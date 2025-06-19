package io.github.luyang.api.uac;

import io.github.luyang.api.uac.model.GetAuthUserParam;
import io.github.luyang.api.uac.model.GetAuthUserResult;
import io.github.luyang.starter.base.api.Result;

public interface RemoteAuthUserService {

	Result<GetAuthUserResult> getAuthUser(GetAuthUserParam getAuthUserParam);
}
