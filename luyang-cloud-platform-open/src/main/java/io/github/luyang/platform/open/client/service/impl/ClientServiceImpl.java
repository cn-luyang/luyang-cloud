package io.github.luyang.platform.open.client.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.base.converter.ClientConverter;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.client.domain.ClientBO;
import io.github.luyang.platform.open.client.domain.ClientCommand;
import io.github.luyang.platform.open.client.repository.ClientRepository;
import io.github.luyang.platform.open.client.repository.model.ClientDO;
import io.github.luyang.platform.open.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户端业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final ClientConverter converter;
	private final ClientRepository repository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 创建新客户端
	 *
	 * @param command 客户端创建命令对象
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	@Override
	@Transactional
	public ClientBO createClient(ClientCommand command) {

		boolean hasClientName = this.repository.existsClientName(command.clientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		ClientDO clientDO = this.converter.toDO(command, passwordEncoder);
		this.repository.save(clientDO);

		return this.converter.toBO(clientDO);
	}

	/**
	 * 根据客户端ID删除
	 *
	 * @param clientId 客户端ID
	 * @author yang.lu
	 */
	@Override
	public void deleteClient(String clientId) {
		// 校验客户端是否存在
		ClientDO ClientDO = this.repository.findByClientId(clientId);
		if (BeanUtil.isEmpty(ClientDO)) {
			logger.info("客户端未找到或已删除，ClientId:[{}]", clientId);
			return;
		}

		boolean removeSuccess = this.repository.removeByClientId(clientId);
		if (removeSuccess) {
			logger.info("客户端删除成功，ClientId:[{}]", clientId);
		} else {
			logger.error("客户端删除失败，ClientId:[{}]", clientId);
		}
	}

	/**
	 * 更新客户端信息
	 *
	 * @param command 客户端更新命令对象
	 * @author yang.lu
	 */
	@Override
	public void updateClient(ClientCommand command) {

		String clientId = command.clientId();

		// 校验客户端是否存在
		ClientDO clientDO = this.repository.findByClientId(clientId);
		ClientError.NOT_FOUND_CLIENT.notNull(clientDO);

		// 客户端名称变更时校验唯一性
		String newClientName = command.clientName();
		String oldClientName = clientDO.getClientId();

		// 如客户端名称有变更，校验新名称的唯一性
		if (StrUtil.isNotEmpty(newClientName) && !StrUtil.equals(oldClientName, newClientName)) {
			boolean hasClientName = this.repository.existsClientName(command.clientName());
			ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);
		}

		// 命令对象转换为数据对象并更新客户端数据
		ClientDO newClientDO = this.converter.toDO(command, clientDO);
		boolean updateSuccess = this.repository.updateById(newClientDO);

		if (updateSuccess) {
			logger.info("更新客户端成功，ClientId:[{}]", clientId);
		} else {
			logger.error("更新客户端失败，可能客户端不存在或未修改。ClientId:[{}]", clientId);
		}
	}

	/**
	 * 获取所有客户端列表
	 *
	 * @return 所有客户端业务对象
	 * @author yang.lu
	 */
	@Override
	public List<ClientBO> getAllClients() {

		List<ClientDO> clientDOs = this.repository.findAll();
		if (CollUtil.isEmpty(clientDOs)) {
			return Collections.emptyList();
		}

		return clientDOs.stream()
			.map(converter::toBO)
			.collect(Collectors.toList());
	}
}
