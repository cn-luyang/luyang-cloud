package io.github.luyang.platform.open.auth.service;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.convert.AuthConvert;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.auth.repository.AuthRequestRepository;
import io.github.luyang.platform.open.auth.repository.entity.AuthRequestEntity;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 认证服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthConvert authConvert;
	private final ClientService clientService;
	private final HttpServletResponse httpServletResponse;
	private final AuthRequestRepository authRequestRepository;

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
		// 执行认证逻辑
		authenticatorHandler.authenticate(loginRequest);

		return LoginResponse.builder().build();
	}

	@SneakyThrows
	public void authorize(AuthorizeRequest authorizeRequest) {

		// 校验响应类型是否为 code
		ClientError.INVALID_CLIENT.isTrue(
			AuthConstant.RESPONSE_TYPE_CODE.equals(authorizeRequest.getResponseType())
		);

		// 校验 client_id 是否存在
		ClientId clientId = ClientId.build(authorizeRequest.getClientId());
		GetClientResponse client = clientService.getClient(clientId);
		ClientError.INVALID_CLIENT.notNull(client);

		// 校验 redirect_uri 是否为注册的重定向地址
		ClientError.INVALID_CLIENT.isTrue(
			client.getRedirectUris().contains(authorizeRequest.getRedirectUri())
		);

		// 检查是否登录（通过 login_token 判断）
		if (StrUtil.isBlank(authorizeRequest.getLoginToken())) {
			// 保存授权请求，方便登录完成后恢复原始请求流程
			AuthRequestEntity authRequestEntity = authConvert.toAuthRequestEntity(authorizeRequest);
			authRequestRepository.save(authRequestEntity);
			// 构造登录页面地址，并附带当前授权请求 ID（用于登录后继续授权流程）
			String loginUri = UriComponentsBuilder.fromPath("https://xxx/login")
				.queryParam(AuthConstant.AUTHORIZE_REQUEST_ID, authRequestEntity.getId())
				.build()
				.toUriString();
			httpServletResponse.sendRedirect(loginUri);
			return;
		}
	}
}
