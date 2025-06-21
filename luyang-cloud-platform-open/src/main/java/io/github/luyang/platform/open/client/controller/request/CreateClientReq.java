package io.github.luyang.platform.open.client.controller.request;

import io.github.luyang.platform.open.base.enums.GrantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * 创建客户端请求体
 *
 * @author yang.lu
 */
@Getter
@Setter
public class CreateClientReq {

	@NotBlank(message = "{validation.notBlank.clientName}")
	@Size(max = 64, message = "{validation.size.clientName}")
	private String clientName;

	@Positive(message = "{validation.positive.accessTokenValidity }")
	private Integer accessTokenValidity;

	@Positive(message = "{validation.positive.refreshTokenValidity}")
	private Integer refreshTokenValidity;

	@NotEmpty(message = "{validation.notEmpty.authorizedGrantTypes}")
	private Set<GrantType> grantTypes;

	@Size(min = 1, max = 5, message = "{validation.size.redirectUris}")
	private Set<@URL String> redirectUris;

	private Boolean autoApprove;

	private String description;
}
