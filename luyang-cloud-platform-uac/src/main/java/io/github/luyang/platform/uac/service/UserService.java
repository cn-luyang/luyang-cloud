package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.beans.request.UserCreateRequest;

/**
 * @author yang.lu
 */
public interface UserService {

	String create(UserCreateRequest request);
}
