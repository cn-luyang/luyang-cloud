package io.github.luyang.platform.uac.beans.convert;

import io.github.luyang.platform.uac.beans.AccountEntity;
import io.github.luyang.platform.uac.beans.command.CreateAccountCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 账号相关对象转换器
 *
 * @author yang.lu
 */
@Mapper
public interface AccountConvert {

	List<AccountEntity> buildAccountEntities(List<CreateAccountCommand> createAccountCommands);
}
