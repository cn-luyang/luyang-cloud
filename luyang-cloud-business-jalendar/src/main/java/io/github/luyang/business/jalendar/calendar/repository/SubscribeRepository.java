package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.repository.mapper.SubscribeMapper;
import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 日历数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class SubscribeRepository extends ServiceImpl<SubscribeMapper, SubscribeDO> {

	private final SubscribeMapper subscribeMapper;

	public SubscribeDO findByUserIdAndCalendarId(String userId, String calendarId) {
		return this.lambdaQuery()
			.eq(SubscribeDO::getUserId, userId)
			.eq(SubscribeDO::getCalendarId, calendarId)
			.one();
	}
}
