package io.github.luyang.platform.open.client.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class GetClientResponse {

	private String clientId;

	private String clientName;

	private Integer accessTokenValidity;

	private Integer refreshTokenValidity;

	private List<String> grantTypes;

	private List<String> redirectUris;

	private Boolean autoApprove;

	private String description;
}
