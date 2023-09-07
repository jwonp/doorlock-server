package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.ReservedRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservedRequestRepository
  extends JpaRepository<ReservedRequest, Long> {
  @Query(value = "SELECT A FROM ReservedRequest A WHERE A.isAccepted = false")
  List<ReservedRequest> getAcceptedRequests();
}
