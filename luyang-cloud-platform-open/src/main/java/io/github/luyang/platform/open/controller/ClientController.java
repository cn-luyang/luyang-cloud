package io.github.luyang.platform.open.controller;

import io.github.luyang.platform.open.beans.request.ClientCreateRequest;
import io.github.luyang.platform.open.service.ClientService;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端相关控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	public Result<String> create(@RequestBody ClientCreateRequest request) {
		String clientId = clientService.create(request);
		return Result.success(clientId);
	}
}
