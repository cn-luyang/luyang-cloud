package io.github.luyang.platform.open.controller;

import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.vo.CreateClientVO;
import io.github.luyang.platform.open.service.ClientService;
import io.github.luyang.starter.base.api.Result;
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
	public Result<CreateClientVO> create(@Valid @RequestBody CreateClientDTO createClientDTO) {
		CreateClientVO createClientVO = clientService.create(createClientDTO);
		return Result.success(createClientVO);
	}
}
