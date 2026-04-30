package io.github.luyang.platform.uaa.service;

import io.github.luyang.platform.uaa.beans.payload.command.AuthorizeCommand;
import io.github.luyang.platform.uaa.beans.payload.command.LoginCommand;
import io.github.luyang.platform.uaa.beans.payload.command.TokenApplyCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.TokenVO;

public interface AuthService {

	void login(LoginCommand command);

	void authorize(AuthorizeCommand command);

	TokenVO applyToken(TokenApplyCommand command);
}
