package io.github.luyang.platform.uac.beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "user", autoResultMap = true)
public class RsaKeyDO {

	@TableId
	private String keyId;
	private String publicKey;
    private String privateKey;
}
