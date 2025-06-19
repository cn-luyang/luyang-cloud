package io.github.luyang.api.uac.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class GetAuthUserResult {

    private String userId;
}
