package io.github.luyang.platform.open.token.domain;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Token业务领域对象
 * 封装客户端的核心业务属性和可能的业务方法
 *
 * @param id                      主键ID
 * @param clientId                客户端ID
 * @param userId                  用户ID
 * @param attachedInfoMap         附带信息
 * @param accessToken             访问令牌
 * @param refreshToken            刷新令牌
 * @param accessTokenExpiresTime  访问令牌过期时间
 * @param refreshTokenExpiresTime 刷新令牌过期时间
 * @param createdBy               创建人
 * @param createdTime             创建时间
 * @param updatedBy               更新人
 * @param updatedTime             更新时间
 * @param deleted                 是否删除
 * @author yang.lu
 */
public record TokenDomain(
	String id,
	String clientId,
	String userId,
	Map<String, Object> attachedInfoMap,
	String accessToken,
	String refreshToken,
	LocalDateTime accessTokenExpiresTime,
	LocalDateTime refreshTokenExpiresTime,
	String createdBy,
	LocalDateTime createdTime,
	String updatedBy,
	LocalDateTime updatedTime,
	Boolean deleted
) {

	/**
	 * 判断 Access Token 是否已过期
	 *
	 * @return 如果当前时间在过期时间之后，则返回 true，否则返回 false。
	 * @author yang.lu
	 */
	public boolean accessTokenIsExpired() {
		return null != accessTokenExpiresTime && LocalDateTime.now().isAfter(accessTokenExpiresTime);
	}

	/**
	 * 判断 Refresh Token 是否已过期
	 *
	 * @return 如果当前时间在过期时间之后，则返回 true，否则返回 false。
	 * @author yang.lu
	 */
	public boolean refreshTokenIsExpired() {
		return null != refreshTokenExpiresTime && LocalDateTime.now().isAfter(refreshTokenExpiresTime);
	}
}
