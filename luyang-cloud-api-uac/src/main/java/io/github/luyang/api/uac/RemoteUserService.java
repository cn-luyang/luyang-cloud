package io.github.luyang.api.uac;

import io.github.luyang.api.uac.model.VerifyAccountParam;
import io.github.luyang.api.uac.model.VerifyAccountResult;
import io.github.luyang.starter.base.api.Result;

public interface RemoteUserService {

	Result<VerifyAccountResult> verifyAccount(VerifyAccountParam verifyAccountParam);
}
