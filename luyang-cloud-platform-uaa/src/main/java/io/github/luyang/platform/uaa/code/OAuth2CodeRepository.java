package io.github.luyang.platform.uaa.code;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class OAuth2CodeRepository extends ServiceImpl<OAuth2CodeMapper, OAuth2CodeEntity> {

}
