package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.contract.AccountAuthCMD;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateResult;
import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.beans.payload.LoginVO;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface AuthConvert {

	AccountAuthCMD buildAccountAuthCMD(LoginDTO loginDTO);

	TokenCreateCMD buildTokenCreateCMD(AccountAuthResult authenticate);

	LoginVO buildLoginVO(TokenCreateResult tokenCreateResult, AccountAuthResult accountAuthResult);
}
