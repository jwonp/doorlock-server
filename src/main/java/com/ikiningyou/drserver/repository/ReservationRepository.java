package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationRepository
  extends JpaRepository<Reservation, Long> {
  @Query("SELECT A from Reservation A JOIN FETCH A.user")
  List<Reservation> findAllReservations();

  @Transactional
  @Modifying
  @Query("delete from Reservation where id in :ids")
  void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
