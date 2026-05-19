package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.command.CreateUserCommand;

public interface UserService {

	String createUser(CreateUserCommand command);
}
