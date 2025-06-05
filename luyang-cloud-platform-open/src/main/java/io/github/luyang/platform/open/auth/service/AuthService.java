package io.github.luyang.platform.open.auth.service;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.AuthorizeResponse;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.convert.AuthConvert;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.auth.repository.AuthorizeRequestRepository;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

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
	private final HttpServletRequest httpServletRequest;
	private final HttpServletResponse httpServletResponse;
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

		// 检查是否登录（通过 login_token 判断），后续改用从Cookie中获取
		String loginToken = httpServletRequest.getHeader(AuthConstant.LOGIN_TOKEN);
		if (StrUtil.isBlank(loginToken)) {
			// 保存授权请求，方便登录完成后恢复原始请求流程
			AuthorizeRequestEntity authorizeRequestEntity = authConvert.toAuthorizeRequestEntity(authorizeRequest);
			authorizeRequestRepository.save(authorizeRequestEntity);
			// 构造登录页面地址，并附带当前授权请求 ID（用于登录后继续授权流程）
			String loginUri = UriComponentsBuilder.fromPath("https://xxx/login")
				.queryParam(AuthConstant.AUTH_REQUEST_ID, authorizeRequestEntity.getId())
				.build()
				.toUriString();
			httpServletResponse.sendRedirect(loginUri);
			return;
		}

		// TODO: 不想动了，怎么办，谁来帮我写...
	}
}
