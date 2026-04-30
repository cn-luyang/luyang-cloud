package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.payload.command.UserCreateCommand;

public interface UserService {

	String create(UserCreateCommand command);
}
