package io.github.luyang.platform.uac.service.impl;

import io.github.luyang.platform.uac.beans.vo.RsaKeyVO;
import io.github.luyang.platform.uac.repository.RsaKeyRepository;
import io.github.luyang.platform.uac.service.RsaKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class RsaKeyServiceImpl implements RsaKeyService {

	private final RsaKeyRepository rsaKeyRepository;


}
