package io.github.luyang.platform.open.client.controller;

import io.github.luyang.platform.open.client.controller.request.CreateClientRequest;
import io.github.luyang.platform.open.client.controller.request.UpdateClientRequest;
import io.github.luyang.platform.open.client.controller.response.CreateClientResponse;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
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
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	public Result<CreateClientResponse> create(@Valid @RequestBody CreateClientRequest createClientRequest) {
		return Result.success(clientService.createClient(createClientRequest));
	}

	@DeleteMapping("/{clientId}")
	public Result<Void> delete(@PathVariable("clientId") String clientId) {
		clientService.deleteClient(clientId);
		return Result.success();
	}

	@PutMapping
	public Result<Void> update(@Valid @RequestBody UpdateClientRequest updateClientRequest) {
		clientService.updateClient(updateClientRequest);
		return Result.success();
	}

	@GetMapping("/{clientId}")
	public Result<GetClientResponse> get(@PathVariable("clientId") String clientId) {
		return Result.success(clientService.getClient(clientId));
	}
}
