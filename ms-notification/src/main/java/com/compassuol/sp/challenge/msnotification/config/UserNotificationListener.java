package com.compassuol.sp.challenge.msnotification.config;

import com.compassuol.sp.challenge.msnotification.model.dto.UserNotificationDTO;
import com.compassuol.sp.challenge.msnotification.service.impl.UserNotificationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserNotificationListener {

    private UserNotificationServiceImpl service;
    @RabbitListener(queues = "msuser-notification")
    public void onReceiveNotification(UserNotificationDTO notificationDTO) {
        service.saveNotification(notificationDTO);
    }
}
