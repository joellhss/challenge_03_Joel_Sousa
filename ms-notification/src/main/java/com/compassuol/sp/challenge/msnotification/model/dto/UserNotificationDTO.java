package com.compassuol.sp.challenge.msnotification.model.dto;

import com.compassuol.sp.challenge.msnotification.enums.EventsForNotification;
import com.compassuol.sp.challenge.msnotification.model.entity.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNotificationDTO {
    private String email;
    private EventsForNotification event;
    private String date;

    public UserNotificationDTO toDTO(UserNotification entity) {
        setDate(entity.getDate().toString());
        setEmail(entity.getEmail());
        setEvent(entity.getEvent());
        return this;
    }

    public UserNotification toEntity() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 9, true)
                .toFormatter();


        UserNotification userNotification = new UserNotification();
        userNotification.setDate(LocalDateTime.parse(getDate(), formatter));
        userNotification.setEmail(getEmail());
        userNotification.setEvent(getEvent());
        return userNotification;
    }
}
