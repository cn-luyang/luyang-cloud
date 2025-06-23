package io.github.luyang.api.uac;

import io.github.luyang.api.uac.dto.AccountAuthDTO;
import io.github.luyang.api.uac.param.AccountAuthParam;
import io.github.luyang.starter.base.api.Result;

/**
 * 用户RPC服务接口
 *
 * @author yang.lu
 */
public interface UserServiceRpc {

	Result<AccountAuthDTO> accountAuth(AccountAuthParam authUserParam);
}
