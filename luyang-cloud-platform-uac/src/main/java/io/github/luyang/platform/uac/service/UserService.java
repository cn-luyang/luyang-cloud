package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.domain.UserDomain;
import io.github.luyang.platform.uac.beans.request.UserCreateReq;

/**
 * @author yang.lu
 */
public interface UserService {

	String create(UserCreateReq userCreateReq);

	UserDomain getByEmail(String email);
}
