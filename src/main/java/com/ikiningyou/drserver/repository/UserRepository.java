package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
  @Transactional
  @Modifying
  @Query("delete from User where id in :ids")
  void deleteAllByIdInQuery(@Param("ids") List<String> ids);
}
