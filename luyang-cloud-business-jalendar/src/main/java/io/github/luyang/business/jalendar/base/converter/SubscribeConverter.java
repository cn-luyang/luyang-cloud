package io.github.luyang.business.jalendar.base.converter;

import io.github.luyang.business.jalendar.calendar.controller.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
import io.github.luyang.starter.security.util.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 日历订阅对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {SecurityUtil.class})
public interface SubscribeConverter {

	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	SubscribeCommand toCommand(CalendarSubscribeRequest request);

	SubscribeDO toDO(SubscribeCommand command);
}
