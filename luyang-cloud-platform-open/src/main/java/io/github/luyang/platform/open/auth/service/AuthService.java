package io.github.luyang.platform.open.auth.service;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.convert.AuthorizeRequestConvert;
import io.github.luyang.platform.open.auth.convert.LoginRequestConvert;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.auth.repository.AuthorizeRequestRepository;
import io.github.luyang.platform.open.auth.repository.LoginRequestRepository;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import io.github.luyang.platform.open.auth.repository.entity.LoginRequestEntity;
import io.github.luyang.platform.open.base.config.properties.AuthProperties;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.enums.error.LoginError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

/**
 * 认证服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthService {

	private final AuthProperties authProperties;

	private final LoginRequestConvert loginRequestConvert;
	private final AuthorizeRequestConvert authorizeRequestConvert;

	private final ClientService clientService;

	private final HttpServletResponse httpServletResponse;
	private final LoginRequestRepository loginRequestRepository;
	private final AuthorizeRequestRepository authorizeRequestRepository;

	/**
	 * 登录
	 *
	 * @param loginRequest 登录请求体
	 * @return 登录响应体
	 * @author yang.lu
	 */
	public LoginResponse login(LoginRequest loginRequest) {

		// 获取对应的 GrantType 枚举实例
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginRequest.getLoginType());

		// 获取对应授权类型的认证处理器
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 授权请求ID
		String authorizeRequestId = loginRequest.getAuthorizeRequestId();
		// 获取对应的授权请求信息
		AuthorizeRequestEntity authorizeRequestEntity = authorizeRequestRepository.getById(authorizeRequestId);
		LoginError.INVALID_AUTHORIZE_REQUEST_ID.notNull(authorizeRequestEntity);

		// 校验授权请求是否已过期
		LoginError.INVALID_AUTHORIZE_REQUEST_ID.isFalse(
			authorizeRequestEntity.getExpireTime().isBefore(LocalDateTime.now())
		);

		// 执行认证逻辑
		String userId = authenticatorHandler.authenticate(loginRequest);
		LoginError.INVALID_ACCOUNT_OR_PASSWORD.notNull(userId);

		LoginRequestEntity loginRequestEntity = loginRequestConvert.toEntity(authorizeRequestId, userId);
		loginRequestRepository.save(loginRequestEntity);

		return LoginResponse.builder()
			.loginId(String.valueOf(loginRequestEntity.getId()))
			.expireTime(loginRequestEntity.getExpireTime())
			.build();
	}

	@SneakyThrows
	public void authorize(AuthorizeRequest authorizeRequest) {

		// 检查是否登录（通过 login_id 判断）
		if (StrUtil.isBlank(authorizeRequest.getLoginId())) {

			// 校验 client_id 是否存在
			ClientId clientId = ClientId.build(authorizeRequest.getClientId());
			GetClientResponse client = clientService.getClient(clientId);
			ClientError.INVALID_CLIENT.notNull(client);

			// 校验 redirect_uri 是否为注册的重定向地址
			ClientError.INVALID_CLIENT.isTrue(
				client.getRedirectUris().contains(authorizeRequest.getRedirectUri())
			);

			// 保存授权请求，方便登录完成后恢复原始请求流程
			AuthorizeRequestEntity authorizeRequestEntity = authorizeRequestConvert.toEntity(authorizeRequest);
			authorizeRequestRepository.save(authorizeRequestEntity);

			// 构造登录页面地址，并附带当前授权请求 ID（用于登录后继续授权流程）
			String loginUri = UriComponentsBuilder
				.fromPath(authProperties.getLoginUrl())
				.queryParam(AuthConstant.AUTHORIZE_REQUEST_ID, authorizeRequestEntity.getId())
				.build()
				.toUriString();

			httpServletResponse.sendRedirect(loginUri);
			return;
		}
	}
}
