package io.github.luyang.platform.uaa.client.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import io.github.luyang.starter.mybatis.support.type.StringListTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "client", autoResultMap = true)
public class ClientEntity extends BaseEntity {

	/**
	 * 客户端ID
	 */
	@TableId
	private String clientId;

	/**
	 * 客户端名称
	 */
	private String clientName;

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
	 * 描述
	 */
	private String description;
}
