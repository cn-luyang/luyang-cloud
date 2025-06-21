package io.github.luyang.platform.open.client.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 获取客户端响应体
 *
 * @author yang.lu
 */
@Getter
@Setter
public class GetClientRes {

	private String clientId;

	private String clientName;

	private Integer accessTokenValidity;

	private Integer refreshTokenValidity;

	private List<String> grantTypes;

	private List<String> redirectUris;

	private Boolean autoApprove;

	private String description;
}
