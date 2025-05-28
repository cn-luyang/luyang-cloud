package io.github.luyang.platform.open.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.enums.GrantType;
import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.dto.UpdateClientDTO;
import io.github.luyang.platform.open.model.entity.ClientEntity;
import io.github.luyang.platform.open.model.vo.GetClientVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mapper(
	componentModel = "spring",
	imports = {
		RandomUtil.class, IdUtil.class, StrUtil.class, ListUtil.class, CollUtil.class
	}
)
public interface ClientConvert {

	@Mapping(target = "clientId", expression = "java(\"cli_\" + RandomUtil.randomString(16))")
	@Mapping(target = "clientSecretPlain", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "clientSecret", expression = "java(passwordEncoder.encode(IdUtil.simpleUUID()))")
	@Mapping(target = "grantTypes", source = "grantTypes", qualifiedByName = "mapGrantTypes")
	@Mapping(target = "redirectUris", source = "redirectUris", qualifiedByName = "mapRedirectUris")
	ClientEntity createDtoToEntity(CreateClientDTO dto, @Context PasswordEncoder passwordEncoder);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "clientId", ignore = true)
	@Mapping(target = "clientSecretPlain", ignore = true)
	@Mapping(target = "clientSecret", ignore = true)
	@Mapping(target = "grantTypes", source = "grantTypes", qualifiedByName = "mapGrantTypes")
	@Mapping(target = "redirectUris", source = "redirectUris", qualifiedByName = "mapRedirectUris")
	void updateDtoToEntity(UpdateClientDTO dto, @MappingTarget ClientEntity entity);

	GetClientVO entityToGetVO(ClientEntity clientEntity);

	@Named("mapGrantTypes")
	static List<String> mapGrantTypes(Set<GrantType> grantTypes) {
		if (grantTypes == null) {
			return null;
		}

		return grantTypes.stream()
			.filter(Objects::nonNull)
			.map(GrantType::getCode)
			.toList();
	}

	@Named("mapRedirectUris")
	static List<String> mapRedirectUris(Set<String> uris) {
		if (CollUtil.isEmpty(uris)) {
			return null;
		}

		return ListUtil.of(StrUtil.join(",", uris));
	}
}
