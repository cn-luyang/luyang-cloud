package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa.client.beans.body.ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.body.ClientCreateResponse;
import io.github.luyang.starter.base.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
	public Result<ClientCreateResponse> create(@RequestBody ClientCreateRequest clientCreateRequest) {
		return Result.success(clientService.create(clientCreateRequest));
	}
}
