package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Authority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
  Optional<Authority> findByUser(String user);
}
