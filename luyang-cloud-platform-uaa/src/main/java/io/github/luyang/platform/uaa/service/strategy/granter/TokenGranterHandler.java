package io.github.luyang.platform.uaa.service.strategy.granter;

import io.github.luyang.platform.uaa.beans.payload.command.TokenApplyCommand;

public interface TokenGranterHandler {

	void grant(TokenApplyCommand command);
}
