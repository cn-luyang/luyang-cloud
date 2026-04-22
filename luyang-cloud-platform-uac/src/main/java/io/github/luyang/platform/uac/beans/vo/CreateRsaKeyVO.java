package io.github.luyang.platform.uac.beans.vo;

import lombok.Builder;

/**
 * @author yang.lu
 */
@Builder
public record CreateRsaKeyVO(String keyId, String pubKey) {
}
