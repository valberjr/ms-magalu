package com.example.ms_magalu.service.strategy;

import com.example.ms_magalu.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationStrategyFactory {

    private final Map<String, NotificationStrategy> strategyMap;

    public NotificationStrategyFactory(Map<String, NotificationStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public NotificationStrategy getNotificationStrategy(String channel) {
        return strategyMap.get(channel);
    }

    public void execute(NotificationDto dto) {
        var strategy = getNotificationStrategy(dto.channel());
        strategy.sendNotification(dto);
    }
}
