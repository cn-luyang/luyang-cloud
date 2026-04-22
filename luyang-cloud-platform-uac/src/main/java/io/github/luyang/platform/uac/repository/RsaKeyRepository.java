package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.RsaKeyDO;
import io.github.luyang.platform.uac.mapper.RsaKeyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class RsaKeyRepository extends ServiceImpl<RsaKeyMapper, RsaKeyDO> {

	@Override
	public boolean save(RsaKeyDO rsaKeyDO) {
		return super.save(rsaKeyDO);
	}
}
