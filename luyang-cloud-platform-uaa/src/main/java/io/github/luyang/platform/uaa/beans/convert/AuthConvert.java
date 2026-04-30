package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.dto.TempTicketDTO;
import io.github.luyang.platform.uaa.beans.payload.command.AuthorizeCommand;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface AuthConvert {

	TokenCreateCMD buildTokenCreateCMD(AccountAuthResult authenticate);

	TempTicketDTO buildTempTicketDTO(String userId, AuthorizeCommand command);
}
