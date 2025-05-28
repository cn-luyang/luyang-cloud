package io.github.luyang.platform.open.controller;

import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.dto.UpdateClientDTO;
import io.github.luyang.platform.open.model.vo.CreateClientVO;
import io.github.luyang.platform.open.model.vo.GetClientVO;
import io.github.luyang.platform.open.service.ClientService;
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
	public Result<CreateClientVO> create(@Valid @RequestBody CreateClientDTO createClientDTO) {
		CreateClientVO createClientVO = clientService.createClient(createClientDTO);
		return Result.success(createClientVO);
	}

	@DeleteMapping("/{clientId}")
	public Result<Void> delete(@PathVariable("clientId") String clientId) {
		clientService.deleteClient(clientId);
		return Result.success();
	}

	@PutMapping
	public Result<Void> update(@Valid @RequestBody UpdateClientDTO updateClientDTO) {
		clientService.updateClient(updateClientDTO);
		return Result.success();
	}

	@GetMapping("/{clientId}")
	public Result<GetClientVO> get(@PathVariable("clientId") String clientId) {
		return Result.success(clientService.getClient(clientId));
	}
}
