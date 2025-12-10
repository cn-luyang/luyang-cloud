package io.github.luyang.platform.uaa._common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yang.lu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = LoginProperties.PREFIX)
public class LoginProperties {

	public static final String PREFIX = "luyang.uaa.login";

	/** 登录页面地址 */
	private String pageUrl;

}
