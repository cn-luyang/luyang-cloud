package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2CodeService {

	private final OAuth2CodeRepository oAuth2CodeRepository;

	public void create(OAuth2CodeCreateParam param) {

	}
}
