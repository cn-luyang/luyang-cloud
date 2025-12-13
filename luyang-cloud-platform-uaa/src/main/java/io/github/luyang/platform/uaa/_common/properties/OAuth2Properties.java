package io.github.luyang.platform.uaa._common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yang.lu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = OAuth2Properties.PREFIX)
public class OAuth2Properties {

	public static final String PREFIX = "luyang.uaa.oauth2";

	/**
	 * 登录页面地址
	 */
	private String loginPageUrl;

	private String callbackUrl;
}
