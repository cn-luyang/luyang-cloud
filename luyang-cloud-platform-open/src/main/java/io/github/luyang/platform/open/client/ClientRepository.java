package io.github.luyang.platform.open.client;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientEntity> {

	private final ClientMapper clientMapper;

	public boolean existsClientName(String clientName) {
		return this.lambdaQuery().eq(ClientEntity::getClientName, clientName).exists();
	}

	@Override
	public boolean save(ClientEntity entity) {
		return super.save(entity);
	}
}
