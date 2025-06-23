package io.github.luyang.platform.open.client.controller.request;

import io.github.luyang.starter.mybatis.beans.PageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户端分页查询请求
 *
 * @author yang.lu
 */
@Getter
@Setter
public class ClientQueryRequest extends PageQuery {

	private String clientId;
	private String clientName;
}
