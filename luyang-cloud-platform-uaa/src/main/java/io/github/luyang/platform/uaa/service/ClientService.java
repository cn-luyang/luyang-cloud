package io.github.luyang.platform.uaa.service;

import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.payload.command.ClientCreateCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.CreateClientVO;

public interface ClientService {

	CreateClientVO create(ClientCreateCommand command);

	ClientDomain getDomain(String clientId);
}
