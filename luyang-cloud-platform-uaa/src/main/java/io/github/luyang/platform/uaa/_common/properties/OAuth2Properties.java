package io.github.luyang.platform.uaa._common.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * 认证授权配置属性
 *
 * @author yang.lu
 */
@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = OAuth2Properties.PREFIX)
public class OAuth2Properties {

	public static final String PREFIX = "luyang.uaa.oauth2";

	/**
	 * 登录页面地址
	 */
	@NotBlank(message = "登录页面地址不能为空")
	private String loginPageUrl;
}
