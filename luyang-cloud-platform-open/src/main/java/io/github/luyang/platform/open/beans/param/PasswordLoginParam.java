package io.github.luyang.platform.open.beans.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Setter
@Getter
public class PasswordLoginParam extends LoginParam {

	private String account;
	private String credential;
}
