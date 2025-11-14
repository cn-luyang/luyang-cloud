package io.github.luyang.platform.uaa._common.enums;

import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseTypeEnum implements IBaseEnum<String> {

	/**
	 * 授权码模式 - 最安全的标准流程
	 * 客户端获取授权码，然后通过后端交换访问令牌
	 * 适用于有后端的传统Web应用
	 */
	CODE("code", "授权码模式"),

	/**
	 * 隐式授权模式 - 已不推荐使用
	 * 授权服务器直接返回访问令牌（在URL片段中）
	 * 存在安全风险，现已被 PKCE 增强的授权码模式取代
	 */
	TOKEN("token", "隐式授权模式"),

	/**
	 * OpenID Connect 身份认证模式
	 * 直接返回 JWT 格式的 ID Token，包含用户身份信息
	 * 用于纯身份认证场景
	 */
	ID_TOKEN("id_token", "OpenID Connect 身份认证"),

	/**
	 * 混合流 - 授权码 + ID Token
	 * 同时返回授权码和 ID Token
	 * 常用于需要立即获取用户信息同时保持后端安全性的场景
	 */
	CODE_ID_TOKEN("code id_token", "混合流（授权码 + ID Token）"),

	/**
	 * 混合流 - 授权码 + 访问令牌
	 * 同时返回授权码和访问令牌
	 * 适用于需要立即访问API同时保持刷新能力的场景
	 */
	CODE_TOKEN("code token", "混合流（授权码 + 访问令牌）"),

	/**
	 * 混合流 - ID Token + 访问令牌
	 * 同时返回 ID Token 和访问令牌
	 * 适用于需要立即获取用户身份和API访问权限的前端应用
	 */
	ID_TOKEN_TOKEN("id_token token", "混合流（ID Token + 访问令牌）"),

	/**
	 * 混合流 - 完整组合
	 * 同时返回授权码、ID Token 和访问令牌
	 * 提供最完整的初始响应信息
	 */
	CODE_ID_TOKEN_TOKEN("code id_token token", "混合流（完整组合）"),

	/**
	 * 无响应类型
	 * 仅用于检查用户是否已认证和授权，不返回任何令牌
	 * 常用于登录状态检查或前置授权验证
	 */
	NONE("none", "无响应类型");

	private final String code;
	private final String message;
}
