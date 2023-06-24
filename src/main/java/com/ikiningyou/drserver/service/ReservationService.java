package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.repository.ReservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  public Reservation[] getAllReservations() {
    List<Reservation> reservationList = reservationRepository.findAll();
    return reservationList.toArray(new Reservation[reservationList.size()]);
  }

  public Reservation[] getAllReservationWithUser() {
    List<Reservation> reservationList = reservationRepository.findAllReservations();

    return reservationList.toArray(new Reservation[reservationList.size()]);
  }

  public Reservation addReservation(String userId, int roomId, String cardId) {
    Reservation newReservation = Reservation
      .builder()
      .userId(userId)
      .cardId(cardId)
      .roomId(roomId)
      .build();
    try {
      Reservation savedReservation = reservationRepository.save(newReservation);
      return savedReservation;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
