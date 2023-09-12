package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Authority;

import com.ikiningyou.drserver.model.dao.UserDetail;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
  Optional<Authority> findByUserDetail(UserDetail userDetail);
}
