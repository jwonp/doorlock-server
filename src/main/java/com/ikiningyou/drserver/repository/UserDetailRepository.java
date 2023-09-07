package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.UserDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository
  extends JpaRepository<UserDetail, String> {
  Optional<UserDetail> findUserDetailByUsername(String username);
}
