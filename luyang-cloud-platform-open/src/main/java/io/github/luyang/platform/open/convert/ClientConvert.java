package io.github.luyang.platform.open.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.enums.ClientError;
import io.github.luyang.platform.open.enums.GrantType;
import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.entity.ClientEntity;
import io.github.luyang.starter.web.util.SpringUtil;
import org.hibernate.validator.constraints.URL;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClientConvert {

	default ClientEntity dtoToEntity(CreateClientDTO clientDTO) {
		if (null == clientDTO) {
			return null;
		}

		ClientEntity clientEntity = new ClientEntity();

		clientEntity.setClientId("cli_" + RandomUtil.randomString(16));
		clientEntity.setClientName(clientDTO.getClientName());

		String clientSecretPlain = IdUtil.simpleUUID();
		clientEntity.setClientSecretPlain(clientSecretPlain);

		PasswordEncoder passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
		clientEntity.setClientSecret(passwordEncoder.encode(clientSecretPlain));

		Integer accessTokenValidity = clientDTO.getAccessTokenValidity();
		Integer refreshTokenValidity = clientDTO.getRefreshTokenValidity();

		if (null != accessTokenValidity && null != refreshTokenValidity) {
			if (Integer.compare(accessTokenValidity, refreshTokenValidity) == 1) {
				ClientError.TOKEN_VALIDITY_INVALID.exception();
			}
		}

		clientEntity.setAccessTokenValidity(accessTokenValidity);
		clientEntity.setRefreshTokenValidity(refreshTokenValidity);

		List<String> grantTypes = clientDTO.getGrantTypes()
			.stream()
			.filter(Objects::nonNull)
			.map(GrantType::getCode)
			.toList();
		clientEntity.setGrantTypes(grantTypes);

		Set<@URL String> uris = clientDTO.getRedirectUris();
		List<String> redirectUris = CollUtil.isEmpty(uris) ? null : ListUtil.of(StrUtil.join(",", uris));
		clientEntity.setRedirectUris(redirectUris);

		clientEntity.setAutoApprove(clientDTO.getAutoApprove());
		clientEntity.setDescription(clientDTO.getDescription());

		return clientEntity;
	}
}
