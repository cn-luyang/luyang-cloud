package io.github.luyang.api.uac;

import io.github.luyang.api.uac.param.GetUserParam;
import io.github.luyang.api.uac.param.VerifyAccountParam;
import io.github.luyang.api.uac.result.GetUserResult;
import io.github.luyang.api.uac.result.VerifyAccountResult;
import io.github.luyang.starter.base.api.Result;

public interface RemoteUserService {

	Result<VerifyAccountResult> verifyAccount(VerifyAccountParam verifyAccountParam);

	Result<GetUserResult> getUser(GetUserParam getUserParam);
}
