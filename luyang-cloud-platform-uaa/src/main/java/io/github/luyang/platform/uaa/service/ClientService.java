package io.github.luyang.platform.uaa.service;

import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.payload.CreateClientDTO;
import io.github.luyang.platform.uaa.beans.payload.CreateClientVO;

public interface ClientService {

	CreateClientVO create(CreateClientDTO createClientDTO);

	ClientDomain getDomain(String clientId);
}
