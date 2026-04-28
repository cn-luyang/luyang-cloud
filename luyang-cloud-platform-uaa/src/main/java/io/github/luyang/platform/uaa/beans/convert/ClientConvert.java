package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.ClientDO;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.payload.CreateClientDTO;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface ClientConvert {

	ClientDO buildClientDO(CreateClientDTO createClientDTO, String clientSecret);

	ClientDomain buildClientDomain(ClientDO clientDO);
}
