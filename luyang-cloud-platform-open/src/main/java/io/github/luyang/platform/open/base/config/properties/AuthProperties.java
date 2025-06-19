package io.github.luyang.platform.open.base.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yang.lu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = AuthProperties.PREFIX)
public class AuthProperties {

	public static final String PREFIX = "luyang.open.auth";

	/** 登录界面 URL */
	private String loginUrl;

	/**  认证ID 有效期(秒) */
	private Integer authIdValidity = 300;

	/**  登录ID 有效期(秒) */
	private Integer loginIdValidity = 300;
}
