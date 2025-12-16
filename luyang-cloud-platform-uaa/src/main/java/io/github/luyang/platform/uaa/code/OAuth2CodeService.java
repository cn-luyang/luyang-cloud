package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa.code.beans.OAuth2CodeConvert;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateResult;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2CodeService {

	private final OAuth2CodeRepository codeRepository;
	private final OAuth2CodeConvert codeConvert;

	public OAuth2CodeCreateResult create(OAuth2CodeCreateParam param) {
		OAuth2CodeEntity entity = codeConvert.buildEntity(param);
		codeRepository.save(entity);

		return OAuth2CodeCreateResult.build(entity.getCode());
	}
}
