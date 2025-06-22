package io.github.luyang.platform.open.client.controller;

import io.github.luyang.platform.open.client.controller.request.CreateClientReq;
import io.github.luyang.platform.open.client.controller.request.UpdateClientReq;
import io.github.luyang.platform.open.client.controller.response.CreateClientRes;
import io.github.luyang.platform.open.client.controller.response.GetClientRes;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.starter.base.api.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Result<CreateClientRes> create(@Valid @RequestBody CreateClientReq createClientReq) {
		return Result.success(clientService.createClient(createClientReq));
	}

	@DeleteMapping("/{clientId}")
	public Result<Void> delete(@PathVariable("clientId") String clientId) {
		clientService.deleteClient(clientId);
		return Result.success();
	}

	@PutMapping
	public Result<Void> update(@Valid @RequestBody UpdateClientReq updateClientReq) {
		clientService.updateClient(updateClientReq);
		return Result.success();
	}

	@GetMapping("/{clientId}")
	public Result<GetClientRes> get(@PathVariable("clientId") String clientId) {
		return Result.success(clientService.getClient(clientId));
	}
}
