package io.github.luyang.platform.uac.beans.convert;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uac.beans.UserEntity;
import io.github.luyang.platform.uac.beans.command.CreateAccountCommand;
import io.github.luyang.platform.uac.beans.command.CreateUserCommand;
import io.github.luyang.platform.uac.common.enums.business.AccountType;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 对象转换器
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

	UserEntity buildEntity(String userId, CreateUserCommand command);

	default List<CreateAccountCommand> buildCreateAccountCommand(String userId, CreateUserCommand command) {

		List<CreateAccountCommand> commands = new ArrayList<>();

		if (StrUtil.isNotBlank(command.email())) {
			commands.add(CreateAccountCommand.build(userId, command.email(), AccountType.EMAIL));
		}

		return commands;
	}
}
