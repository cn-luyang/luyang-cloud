package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa.client.beans.body.ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.body.ClientCreateResponse;
import io.github.luyang.starter.base.model.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端管理控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	/**
	 * 创建 客户端
	 *
	 * @param request 创建请求参数
	 * @return 客户端创建响应
	 */
	@PostMapping
	public Result<ClientCreateResponse> create(@Valid @RequestBody ClientCreateRequest request) {
		return Result.success(clientService.create(request));
	}
}
