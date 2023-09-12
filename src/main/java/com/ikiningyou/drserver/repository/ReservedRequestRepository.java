package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.ReservedRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReservedRequestRepository
  extends JpaRepository<ReservedRequest, Long> {
  @Query(value = "SELECT A FROM ReservedRequest A WHERE A.isAccepted = false")
  List<ReservedRequest> getAcceptedRequests();

  @Transactional
  @Modifying
  @Query("delete from ReservedRequest where id = :id")
  void deleteAllByIdInQuery(@Param("id") Long id);
}
