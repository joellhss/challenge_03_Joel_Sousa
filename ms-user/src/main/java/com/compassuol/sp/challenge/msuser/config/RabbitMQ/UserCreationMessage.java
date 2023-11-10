package com.compassuol.sp.challenge.msuser.config.RabbitMQ;

import com.compassuol.sp.challenge.msuser.Enums.EventsForNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationMessage {
    private String email;
    private EventsForNotification event;
    private String date;
}
