package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.controller.request.UserCreateReq;
import io.github.luyang.platform.uac.service.domain.UserDomain;

/**
 * @author yang.lu
 */
public interface UserService {

	String create(UserCreateReq userCreateReq);

	UserDomain getByEmail(String email);
}
