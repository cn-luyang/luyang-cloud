package io.github.luyang.business.plan.subscribe;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.plan.subscribe.beans.entity.CalendarSubscribeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarSubscribeRepository extends ServiceImpl<CalendarSubscribeMapper, CalendarSubscribeEntity> {

	private final CalendarSubscribeMapper calendarSubscribeMapper;
}
