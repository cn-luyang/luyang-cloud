package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa._common.enums.error.AuthorizationCodeError;
import io.github.luyang.platform.uaa.code.beans.AuthorizationCodeConvert;
import io.github.luyang.platform.uaa.code.beans.AuthorizationCodeDomain;
import io.github.luyang.platform.uaa.code.beans.bo.AuthorizationCodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.bo.AuthorizationCodeCreateResult;
import io.github.luyang.platform.uaa.code.beans.entity.AuthorizationCodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 授权码业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthorizationCodeService {

	private final AuthorizationCodeRepository authorizationCodeRepository;
	private final AuthorizationCodeConvert authorizationCodeConvert;

	/**
	 * 创建授权码
	 *
	 * @param param 创建请求参数
	 * @return 授权码创建响应
	 * @author yang.lu
	 */
	public AuthorizationCodeCreateResult create(AuthorizationCodeCreateParam param) {

		AuthorizationCodeEntity entity = authorizationCodeConvert.buildEntity(param);
		boolean hasSuccess = authorizationCodeRepository.save(entity);
		AuthorizationCodeError.CODE_SAVE_FAILED.isTrue(hasSuccess);
		return AuthorizationCodeCreateResult.build(entity.getCode());
	}

	/**
	 * 根据授权码获取领域模型
	 *
	 * @param code 授权码
	 * @return 领域模型
	 * @author yang.lu
	 */
	public AuthorizationCodeDomain getDomainByCode(String code) {
		AuthorizationCodeEntity entity = authorizationCodeRepository.getById(code);
		return authorizationCodeConvert.buildDomain(entity);
	}

	/**
	 * 作废授权码
	 *
	 * @param code 授权码
	 * @author yang.lu
	 */
	public void consumedCode(String code) {
		authorizationCodeRepository.consumedCode(code);
	}
}
