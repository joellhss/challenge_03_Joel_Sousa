package com.compassuol.sp.challenge.msuser.config.RabbitMQ;

import com.compassuol.sp.challenge.msuser.enums.EventsForNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationMessage {
    private String email;
    private EventsForNotification event;
    private String date;
}
