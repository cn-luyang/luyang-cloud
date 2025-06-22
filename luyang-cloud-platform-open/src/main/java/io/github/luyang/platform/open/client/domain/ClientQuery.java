package io.github.luyang.platform.open.client.domain;

/**
 * 客户端查询对象 (Query)
 * 用于在 Service 层内部传递查询条件，与外部请求DTO解耦
 *
 * @param clientId   客户端ID
 * @param clientName 应用名
 * @param grantType  授权类型 (模糊查询)
 * @param pageNum    页码
 * @param pageSize   每页大小
 * @author yang.lu
 */
public record ClientQuery(
	String clientId,
	String clientName,
	String grantType,
	Integer pageNum,
	Integer pageSize
) {
}
