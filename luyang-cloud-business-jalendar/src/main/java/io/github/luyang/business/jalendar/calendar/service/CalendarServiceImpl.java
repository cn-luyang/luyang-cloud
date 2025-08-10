package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.repository.CalendarRepository;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.service.command.CalendarCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 日历业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarConverter calendarConverter;
    private final CalendarRepository calendarRepository;
    private final CalendarSubscribeService calendarSubscribeService;

    @Override
    public String createCalendar(CalendarCommand command) {

        CalendarEntity calendarEntity = calendarConverter.buildEntity(command);
        calendarRepository.save(calendarEntity);

        calendarSubscribeService.initSubscribe(command);

        return command.calendarId();
    }
}
