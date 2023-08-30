package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.LostCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LostCardRepository extends JpaRepository<LostCard, String> {
  @Transactional
  @Modifying
  @Query("delete from LostCard where id = :id")
  void deleteByIdInQuery(@Param("id") String id);
}
