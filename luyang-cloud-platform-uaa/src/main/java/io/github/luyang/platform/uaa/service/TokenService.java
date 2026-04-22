package io.github.luyang.platform.uaa.service;

import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateResult;

public interface TokenService {

	TokenCreateResult create(TokenCreateCMD tokenCreateCMD);
}
