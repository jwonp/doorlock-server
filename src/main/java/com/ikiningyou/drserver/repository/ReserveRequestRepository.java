package com.ikiningyou.drserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ikiningyou.drserver.model.dao.ReservedRequest;
import java.util.List;


public interface ReserveRequestRepository extends JpaRepository<ReservedRequest,Long>{
    

    @Query("SELECT * FROM ReservedRequest WHERE isAccepted = false")
    List<ReservedRequest> findByNotAccepted();
}
