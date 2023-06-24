package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository
  extends JpaRepository<Reservation, Long> {
  @Query("SELECT A from Reservation A JOIN FETCH A.user")
  List<Reservation> findAllReservations();
}
