package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa._common.enums.error.AuthorizationCodeError;
import io.github.luyang.platform.uaa.code.beans.AuthorizationCodeDomain;
import io.github.luyang.platform.uaa.code.beans.OAuth2CodeConvert;
import io.github.luyang.platform.uaa.code.beans.bo.AuthorizationCodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.bo.AuthorizationCodeCreateResult;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 授权码业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2CodeService {

	private final OAuth2CodeRepository codeRepository;
	private final OAuth2CodeConvert codeConvert;

	/**
	 * 创建授权码
	 *
	 * @param param 创建请求参数
	 * @return 授权码创建响应
	 * @author yang.lu
	 */
	public AuthorizationCodeCreateResult create(AuthorizationCodeCreateParam param) {

		OAuth2CodeEntity entity = codeConvert.buildEntity(param);
		boolean hasSuccess = codeRepository.save(entity);
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
		OAuth2CodeEntity entity = codeRepository.getById(code);
		return codeConvert.buildDomain(entity);
	}

	/**
	 * 作废授权码
	 *
	 * @param code 授权码
	 * @author yang.lu
	 */
	public void consumedCode(String code) {
		codeRepository.consumedCode(code);
	}
}
