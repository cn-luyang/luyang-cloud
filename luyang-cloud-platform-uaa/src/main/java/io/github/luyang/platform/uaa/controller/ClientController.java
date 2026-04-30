package io.github.luyang.platform.uaa.controller;

import io.github.luyang.platform.uaa.beans.payload.command.ClientCreateCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.CreateClientVO;
import io.github.luyang.platform.uaa.service.ClientService;
import io.github.luyang.starter.base.model.Result;
import jakarta.validation.Valid;
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
	public Result<CreateClientVO> create(@Valid @RequestBody ClientCreateCommand command) {
		return Result.success(clientService.create(command));
	}
}
