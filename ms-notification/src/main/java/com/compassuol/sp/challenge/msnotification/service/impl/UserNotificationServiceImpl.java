package com.compassuol.sp.challenge.msnotification.service.impl;

import com.compassuol.sp.challenge.msnotification.exceptions.customExceptions.NotFoundNotificationException;
import com.compassuol.sp.challenge.msnotification.model.dto.UserNotificationDTO;
import com.compassuol.sp.challenge.msnotification.model.entity.UserNotification;
import com.compassuol.sp.challenge.msnotification.repository.UserNotificationRepository;
import com.compassuol.sp.challenge.msnotification.service.UserNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserNotificationServiceImpl implements UserNotificationService {
    UserNotificationRepository repository;
    @Override
    public UserNotificationDTO saveNotification(UserNotificationDTO data) {
        UserNotification save = repository.save(data.toEntity());
        return new UserNotificationDTO().toDTO(save);
    }

    public Page<UserNotificationDTO> getNotifications(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);



        Page<UserNotification> repositoryAll = repository.findAll(pageRequest);

        if(repositoryAll.getSize() < 1) throw new NotFoundNotificationException("No logs registered so far");


        List<UserNotificationDTO> listDto = new ArrayList<>();

        repositoryAll.forEach(entity -> {
            listDto.add(new UserNotificationDTO().toDTO(entity));
        });

        Page<UserNotificationDTO> newPage = new PageImpl<>(listDto, pageRequest, repositoryAll.getTotalElements());

        return newPage;
    }
}

