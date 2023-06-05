package com.ikiningyou.drserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikiningyou.drserver.model.dao.User;

public interface UserRepository extends JpaRepository<User,String> {
    
}
