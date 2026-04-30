package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.ClientDO;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.payload.command.ClientCreateCommand;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface ClientConvert {

	ClientDO buildClientDO(ClientCreateCommand command, String clientSecret);

	ClientDomain buildClientDomain(ClientDO clientDO);
}
