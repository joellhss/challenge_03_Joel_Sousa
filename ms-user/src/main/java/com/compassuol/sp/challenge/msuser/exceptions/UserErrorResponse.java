package com.compassuol.sp.challenge.msuser.exceptions;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserErrorResponse {
    private Integer code;
    private String status;
    private String message;
    private DetailsUserErrorResponse details;

}
@Getter
@Setter
class DetailsUserErrorResponse {
    private String fields;
    private String message;
    public DetailsUserErrorResponse(String fields, String message) {
        this.fields = fields;
        this.message = message;
    }
}
