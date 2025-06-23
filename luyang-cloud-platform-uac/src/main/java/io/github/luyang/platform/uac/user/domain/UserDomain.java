package io.github.luyang.platform.uac.user.domain;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.starter.web.util.SpringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

/**
 * 客户端业务领域对象
 * 封装客户端的核心业务属性和可能的业务方法
 *
 * @param id          主键ID
 * @param userId      用户ID
 * @param zhName      中文名
 * @param enName      英文名
 * @param email       邮箱
 * @param password    密码
 * @param gender      性别 {[0:保密] [1:男] [2:女]}
 * @param createdBy   创建人
 * @param createdTime 创建时间
 * @param updatedBy   更新人
 * @param updatedTime 更新时间
 * @param deleted     是否删除
 * @author yang.lu
 */
public record UserDomain(
	Long id,
	String userId,
	String zhName,
	String enName,
	String email,
	String password,
	Integer gender,
	String createdBy,
	LocalDateTime createdTime,
	String updatedBy,
	LocalDateTime updatedTime,
	Boolean deleted
) {

	public boolean passwordIsNotEmpty() {
		return StrUtil.isNotEmpty(this.password);
	}

	public boolean passwordMatches(CharSequence rawPassword) {
		PasswordEncoder passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
		return passwordEncoder.matches(rawPassword, this.password);
	}
}
