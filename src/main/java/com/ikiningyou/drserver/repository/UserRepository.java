package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
