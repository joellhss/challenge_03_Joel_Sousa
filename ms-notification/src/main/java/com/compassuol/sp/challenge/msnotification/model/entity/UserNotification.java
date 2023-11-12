package com.compassuol.sp.challenge.msnotification.model.entity;

import com.compassuol.sp.challenge.msnotification.enums.EventsForNotification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "notifications-collection")
public class UserNotification {
    @Id
    private String Id;
    private String email;
    private EventsForNotification event;
    private LocalDateTime date;
}
