package com.arjun.event_ticketing_api.repository;

import com.arjun.event_ticketing_api.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByUsername(String username);

}
