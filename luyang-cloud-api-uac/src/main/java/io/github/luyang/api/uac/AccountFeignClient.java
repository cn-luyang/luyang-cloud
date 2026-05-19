package io.github.luyang.api.uac;

import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.starter.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "platform-uac")
public interface AccountFeignClient {

	Result<AccountAuthResponse> accountAuth(AccountAuthRequest accountAuthRequest);
}
