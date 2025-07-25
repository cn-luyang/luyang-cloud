package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.repository.entity.SubscribeEntity;
import io.github.luyang.business.jalendar.calendar.repository.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 日历数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class SubscribeRepository extends ServiceImpl<SubscribeMapper, SubscribeEntity> {

	private final SubscribeMapper subscribeMapper;

	public SubscribeEntity findByUserIdAndCalendarId(String userId, String calendarId) {
		return this.lambdaQuery()
			.eq(SubscribeEntity::getUserId, userId)
			.eq(SubscribeEntity::getCalendarId, calendarId)
			.one();
	}
}
