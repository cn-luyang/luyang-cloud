package io.github.luyang.platform.open.client.repository.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import io.github.luyang.starter.mybatis.support.type.StringListTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 客户端数据库实体对象
 *
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "client", autoResultMap = true)
public class ClientDO extends BaseEntity<ClientDO, Long> {

	/**
	 * 客户端ID
	 */
	private String clientId;

	/**
	 * 客户端名称
	 */
	private String clientName;

	/**
	 * 客户端密钥明文(仅初始化时使用)
	 */
	private String clientSecretPlain;

	/**
	 * 客户端密钥
	 */
	private String clientSecret;

	/**
	 * 访问令牌有效期(秒)
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效期(秒)
	 */
	private Integer refreshTokenValidity;

	/**
	 * 授权类型
	 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> grantTypes;

	/**
	 * 重定向URI
	 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> redirectUris;

	/**
	 * 是否自动批准
	 */
	private Boolean autoApprove;

	/**
	 * 描述
	 */
	private String description;
}
