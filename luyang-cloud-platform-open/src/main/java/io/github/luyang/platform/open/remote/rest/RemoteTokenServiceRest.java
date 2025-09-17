package io.github.luyang.platform.open.remote.rest;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.open.service.TokenService;
import io.github.luyang.starter.base.common.model.Result;
import io.github.luyang.starter.security.AuthUser;
import io.github.luyang.starter.security.remote.openfeign.RemoteTokenServiceApi;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
public class RemoteTokenServiceRest implements RemoteTokenServiceApi {

	private static final Logger logger = LoggerFactory.getLogger(RemoteTokenServiceRest.class);

	private final TokenService tokenService;

	@Override
	public Result<AuthUser> validateToken(String accessToken) {
		logger.info("accessToken:{}", accessToken);
		return Result.success(new AuthUser(
			IdUtil.nanoId(),
			IdUtil.nanoId(),
			"鲁阳",
			"luyang",
			LocalDateTime.now(),
			new HashMap<>()
		));
	}
}
