package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.command.CreateAccountCommand;

import java.util.List;

public interface AccountService {

	void createAccount(List<CreateAccountCommand> createAccountCommands);
}
