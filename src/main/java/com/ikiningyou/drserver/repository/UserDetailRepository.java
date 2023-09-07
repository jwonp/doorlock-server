package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository
  extends JpaRepository<UserDetail, String> {}
