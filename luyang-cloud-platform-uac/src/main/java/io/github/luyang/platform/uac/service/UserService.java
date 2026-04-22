package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.payload.CreateUserDTO;
import io.github.luyang.platform.uac.beans.payload.CreateUserVO;

public interface UserService {

	CreateUserVO create(CreateUserDTO createUserDTO);
}
