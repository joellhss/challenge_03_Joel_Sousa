package com.compassuol.sp.challenge.msnotification.controller;

import com.compassuol.sp.challenge.msnotification.enums.EventsForNotification;
import com.compassuol.sp.challenge.msnotification.model.dto.UserNotificationDTO;
import com.compassuol.sp.challenge.msnotification.service.impl.UserNotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserNotificationControllerTest {
    @InjectMocks
    UserNotificationController controller;
    @Mock
    UserNotificationServiceImpl service;

    @Test
    public void getNotifications_ReturnsList() {
        int size = 10;
        int page = 0;

        UserNotificationDTO userNotificationDTO = new UserNotificationDTO();
        userNotificationDTO.setDate("1997-04-09T00:00:00.000-03:00");
        userNotificationDTO.setEmail("example@email.com");
        userNotificationDTO.setEvent(EventsForNotification.CREATE);


        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserNotificationDTO> pageList = new PageImpl<>(List.of(userNotificationDTO), pageRequest, List.of(userNotificationDTO).size());

        when(service.getNotifications(page, size)).thenReturn(pageList);

        ResponseEntity<Page<UserNotificationDTO>> sut = controller.getNotifications(page, size);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(pageList);
    }
}
