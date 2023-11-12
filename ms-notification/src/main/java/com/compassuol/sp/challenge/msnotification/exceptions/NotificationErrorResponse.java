package com.compassuol.sp.challenge.msnotification.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotificationErrorResponse {
    private Integer code;
    private String status;
    private String message;
    private DetailsNotificationErrorResponse details;

}
@Getter
@Setter
class DetailsNotificationErrorResponse {
    private String fields;
    private String message;
    public DetailsNotificationErrorResponse(String fields, String message) {
        this.fields = fields;
        this.message = message;
    }
}
