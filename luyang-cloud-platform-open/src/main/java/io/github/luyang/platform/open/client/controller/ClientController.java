package io.github.luyang.platform.open.client.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.luyang.platform.open.base.converter.ClientConverter;
import io.github.luyang.platform.open.client.controller.request.ClientCreateRequest;
import io.github.luyang.platform.open.client.controller.request.ClientQueryRequest;
import io.github.luyang.platform.open.client.controller.request.ClientUpdateRequest;
import io.github.luyang.platform.open.client.controller.response.ClientResponse;
import io.github.luyang.platform.open.client.domain.ClientCommand;
import io.github.luyang.platform.open.client.domain.ClientDomain;
import io.github.luyang.platform.open.client.domain.ClientQuery;
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

import java.util.Collections;
import java.util.List;

/**
 * 客户端 RESTful API 控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

	private final ClientConverter converter;
	private final ClientService clientService;

	@PostMapping
	public Result<ClientResponse> create(@Valid @RequestBody ClientCreateRequest request) {
		ClientCommand command = this.converter.toCommand(request);
		ClientDomain clientDomain = this.clientService.create(command);
		return Result.success(this.converter.toResponse(clientDomain));
	}

	@DeleteMapping("/{clientId}")
	public Result<Void> delete(@PathVariable String clientId) {
		clientService.delete(clientId);
		return Result.success();
	}

	@PutMapping
	public Result<Void> update(@Valid @RequestBody ClientUpdateRequest request) {
		ClientCommand command = this.converter.toCommand(request);
		clientService.update(command);
		return Result.success();
	}

	@GetMapping("/{clientId}")
	public Result<ClientResponse> get(@PathVariable String clientId) {
		ClientDomain clientDomain = this.clientService.get(clientId);
		return Result.success(this.converter.toResponse(clientDomain));
	}

	@GetMapping
	public Result<List<ClientResponse>> list() {
		List<ClientDomain> clientDomains = clientService.list();
		if (CollUtil.isEmpty(clientDomains)) {
			return Result.success(Collections.emptyList());
		}

		List<ClientResponse> clientResponses = clientDomains.stream()
			.map(converter::toResponse)
			.toList();

		return Result.success(clientResponses);
	}

	@GetMapping("/page")
	public Result<IPage<ClientResponse>> page(@Valid ClientQueryRequest request) {
		ClientQuery clientQuery = this.converter.toQuery(request);
		IPage<ClientDomain> clientDomainPage = clientService.page(clientQuery);

		IPage<ClientResponse> responsePage = clientDomainPage.convert(this.converter::toResponse);
		return Result.success(responsePage);
	}
}
