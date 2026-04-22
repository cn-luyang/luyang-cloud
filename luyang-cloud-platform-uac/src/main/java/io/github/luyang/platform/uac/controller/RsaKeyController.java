package io.github.luyang.platform.uac.controller;

import io.github.luyang.platform.uac.beans.vo.CreateRsaKeyVO;
import io.github.luyang.platform.uac.service.RsaKeyService;
import io.github.luyang.starter.base.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rsa-key")
public class RsaKeyController {

	private final RsaKeyService rsaKeyService;

	@PostMapping
	public Result<CreateRsaKeyVO> create() {
		return Result.success(null);
	}
}
