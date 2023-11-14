package com.compassuol.sp.challenge.msnotification.service;

import com.compassuol.sp.challenge.msnotification.enums.EventsForNotification;
import com.compassuol.sp.challenge.msnotification.model.dto.UserNotificationDTO;
import com.compassuol.sp.challenge.msnotification.model.entity.UserNotification;
import com.compassuol.sp.challenge.msnotification.repository.UserNotificationRepository;
import com.compassuol.sp.challenge.msnotification.service.impl.UserNotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserNotificationServiceImplTest {
    @InjectMocks
    UserNotificationServiceImpl service;
    @Mock
    UserNotificationRepository repository;
    @Test
    public void saveNotification_withValidData_ReturnsUserNotificationDTO() {
        UserNotificationDTO userNotificationDTO = new UserNotificationDTO();
        userNotificationDTO.setDate("1997-04-09T00:00:00.000");
        userNotificationDTO.setEmail("example@email.com");
        userNotificationDTO.setEvent(EventsForNotification.CREATE);

        UserNotification notification = userNotificationDTO.toEntity();

        when(repository.save(any(UserNotification.class))).thenReturn(notification);

        UserNotificationDTO sut = service.saveNotification(userNotificationDTO);

        assertThat(sut).isNotNull();
        assertThat(sut.getEvent()).isEqualTo(userNotificationDTO.getEvent());
        assertThat(sut.getEmail()).isEqualTo(userNotificationDTO.getEmail());
        assertThat(sut.getDate()).isEqualTo("1997-04-09T00:00");
    }

    @Test
    public void getNotifications_withValidData_ReturnsPageOfUserNotificationDTO() {
        int size = 10;
        int page = 0;

        UserNotificationDTO userNotificationDTO = new UserNotificationDTO();
        userNotificationDTO.setDate("1997-04-09T00:00:00.000");
        userNotificationDTO.setEmail("example@email.com");
        userNotificationDTO.setEvent(EventsForNotification.CREATE);

        UserNotification notification = userNotificationDTO.toEntity();

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UserNotification> pageOfUserNotification = new PageImpl<>(List.of(notification), pageRequest, List.of(notification).size());

        when(repository.findAll(any(PageRequest.class))).thenReturn(pageOfUserNotification);

        Page<UserNotificationDTO> sut = service.getNotifications(page, size);

        assertThat(sut.stream().findAny().get().getEmail()).isEqualTo(userNotificationDTO.getEmail());
        assertThat(sut.stream().findAny().get().getEvent()).isEqualTo(userNotificationDTO.getEvent());
        assertThat(sut.stream().findAny().get().getDate()).isEqualTo("1997-04-09T00:00");
    }
}
