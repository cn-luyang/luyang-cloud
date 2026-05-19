package io.github.luyang.platform.uac.beans.command;

import io.github.luyang.platform.uac.common.enums.business.AccountType;

public record CreateAccountCommand(
	String userId,
	String username,
	AccountType accountType
) {

	public static CreateAccountCommand build(String userId, String username, AccountType accountType) {
		return new CreateAccountCommand(userId, username, accountType);
	}
}
