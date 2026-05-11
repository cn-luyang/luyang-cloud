package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.PasswordDO;
import io.github.luyang.platform.uac.mapper.PasswordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class PasswordRepository extends ServiceImpl<PasswordMapper, PasswordDO> {
}
