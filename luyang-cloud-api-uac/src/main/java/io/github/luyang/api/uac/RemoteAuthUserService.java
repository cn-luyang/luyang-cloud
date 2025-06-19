package io.github.luyang.api.uac;

import io.github.luyang.api.uac.model.GetAuthUserDTO;
import io.github.luyang.api.uac.model.GetAuthUserResult;
import io.github.luyang.starter.base.api.Result;

public interface RemoteAuthUserService {

	Result<GetAuthUserResult> getAuthUser(GetAuthUserDTO getAuthUserDTO);

	String getStr(String name);
}
