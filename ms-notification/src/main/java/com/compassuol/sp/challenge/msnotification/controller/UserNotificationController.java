package com.compassuol.sp.challenge.msnotification.controller;

import com.compassuol.sp.challenge.msnotification.model.dto.UserNotificationDTO;
import com.compassuol.sp.challenge.msnotification.service.impl.UserNotificationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/notification")
@AllArgsConstructor
public class UserNotificationController {
    private UserNotificationServiceImpl service;

    @GetMapping
    public ResponseEntity<Page<UserNotificationDTO>> getNotifications(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {

        Page<UserNotificationDTO> notifications = service.getNotifications(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }
}
