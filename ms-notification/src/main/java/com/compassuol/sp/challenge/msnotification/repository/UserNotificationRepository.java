package com.compassuol.sp.challenge.msnotification.repository;

import com.compassuol.sp.challenge.msnotification.model.entity.UserNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserNotificationRepository extends MongoRepository<UserNotification, String> {
}
