package io.github.luyang.platform.uaa.service;

import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.beans.payload.LoginVO;

public interface AuthService {

	LoginVO login(LoginDTO loginDTO);
}
