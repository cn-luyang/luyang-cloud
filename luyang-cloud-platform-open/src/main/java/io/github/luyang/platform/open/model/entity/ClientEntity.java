package io.github.luyang.platform.open.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import io.github.luyang.starter.mybatis.support.type.StringListTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_open_client", autoResultMap = true)
public class ClientEntity extends BaseEntity<ClientEntity, Long> {

	/** 客户端ID */
	private String clientId;

	/** 客户端名称 */
	private String clientName;

	/** 客户端密钥 */
	private String clientSecret;

	/** 客户端密钥明文(仅初始化时使用) */
	private String clientSecretPlain;

	/** 访问令牌有效期(秒) */
	private Integer accessTokenValidity;

	/** 刷新令牌有效期(秒) */
	private Integer refreshTokenValidity;

	/** 授权类型 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> grantTypes;

	/** 重定向URI */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> redirectUris;

	/** 是否自动批准 */
	private Boolean autoApprove;

	/** 描述 */
	private String description;
}
