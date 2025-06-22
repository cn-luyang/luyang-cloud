package io.github.luyang.platform.open.client.controller;

import cn.hutool.core.collection.CollUtil;
import io.github.luyang.platform.open.base.converter.ClientConverter;
import io.github.luyang.platform.open.client.controller.request.ClientCreateRequest;
import io.github.luyang.platform.open.client.controller.request.ClientUpdateRequest;
import io.github.luyang.platform.open.client.controller.response.ClientResponse;
import io.github.luyang.platform.open.client.domain.ClientBO;
import io.github.luyang.platform.open.client.domain.ClientCommand;
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

	/**
	 * 创建新客户端
	 *
	 * @param request 客户端创建信息的请求体
	 * @return 创建成功的客户端响应
	 * @author yang.lu
	 */
	@PostMapping
	public Result<ClientResponse> createClient(@Valid @RequestBody ClientCreateRequest request) {
		// 转换为 ClientCommand，传递给 Service 层
		ClientCommand command = this.converter.toCommand(request);
		ClientBO clientBO = this.clientService.createClient(command);
		return Result.success(this.converter.toResponse(clientBO));
	}

	/**
	 * 根据 ClientId 删除客户端
	 *
	 * @param clientId 客户端ID
	 * @return 无内容响应
	 * @author yang.lu
	 */
	@DeleteMapping("/{clientId}")
	public Result<Void> deleteClient(@PathVariable String clientId) {
		clientService.deleteClient(clientId);
		return Result.success();
	}

	/**
	 * 更新客户端信息
	 *
	 * @param request 客户端更新信息的请求体
	 * @return 更新后的客户端响应
	 * @author yang.lu
	 */
	@PutMapping("/{clientId}")
	public Result<Void> updateClient(@Valid @RequestBody ClientUpdateRequest request) {
		// 转换为 ClientCommand，传递给 Service 层
		ClientCommand command = this.converter.toCommand(request);
		clientService.updateClient(command);
		return Result.success();
	}

	/**
	 * 获取所有客户端列表
	 *
	 * @return 所有客户端的响应列表
	 * @author yang.lu
	 */
	@GetMapping("/all")
	public Result<List<ClientResponse>> getAllClients() {
		List<ClientBO> clientBOs = clientService.getAllClients();
		if (CollUtil.isEmpty(clientBOs)) {
			return Result.success(Collections.emptyList());
		}

		List<ClientResponse> clientResponses = clientBOs.stream()
			.map(converter::toResponse)
			.toList();

		return Result.success(clientResponses);
	}
}
