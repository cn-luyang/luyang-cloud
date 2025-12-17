package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa._common.enums.error.OAuth2CodeError;
import io.github.luyang.platform.uaa.code.beans.OAuth2CodeConvert;
import io.github.luyang.platform.uaa.code.beans.OAuth2CodeDomain;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateResult;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OAuth2 授权码业务服务
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
	public OAuth2CodeCreateResult create(OAuth2CodeCreateParam param) {

		OAuth2CodeEntity entity = codeConvert.buildEntity(param);
		boolean hasSuccess = codeRepository.save(entity);
		OAuth2CodeError.CODE_SAVE_FAILED.isTrue(hasSuccess);
		return OAuth2CodeCreateResult.build(entity.getCode());
	}

	public OAuth2CodeDomain getDomainByCode(String code) {
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
