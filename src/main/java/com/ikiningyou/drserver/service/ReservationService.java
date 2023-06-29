package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.reservation.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationWithUserResponse;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.repository.ReservationRepository;
import com.ikiningyou.drserver.repository.RoomRepository;
import com.ikiningyou.drserver.repository.UserRepository;
import com.ikiningyou.drserver.util.builder.reservation.ReservationBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoomRepository roomRepository;

  public ReservationResponse[] getAllReservations() {
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponse> reservationList = new ArrayList<ReservationResponse>();
    for (Reservation reservation : reservations) {
      reservationList.add(
        ReservationBuilder.ReservationToReservationResponse(reservation)
      );
    }
    return reservationList.toArray(
      new ReservationResponse[reservationList.size()]
    );
  }

  public ReservationWithUserResponse[] getAllReservationWithUser() {
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationWithUserResponse> reservationList = new ArrayList<ReservationWithUserResponse>();
    for (Reservation reservation : reservations) {
      reservationList.add(
        ReservationBuilder.ReservationToReservationWithUserResponse(reservation)
      );
    }
    return reservationList.toArray(
      new ReservationWithUserResponse[reservationList.size()]
    );
  }

  public ReservationFullResponse[] getFullReservations() {
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationFullResponse> reservationList = new ArrayList<ReservationFullResponse>();
    for (Reservation reservation : reservations) {
      reservationList.add(
        ReservationBuilder.ReservationToReservationFullResponse(reservation)
      );
    }
    return reservationList.toArray(
      new ReservationFullResponse[reservationList.size()]
    );
  }

  public ReservationResponse addReservation(
    String userId,
    int roomId,
    String cardId
  ) {
    Optional<Card> card = cardRepository.findById(cardId);
    Optional<Room> room = roomRepository.findById(roomId);
    Optional<User> user = userRepository.findById(userId);
    if (!card.isPresent() || !room.isPresent() || !user.isPresent()) {
      return null;
    }
    Reservation newReservation = Reservation
      .builder()
      .card(card.get())
      .user(user.get())
      .room(room.get())
      .build();
    try {
      Reservation savedReservation = reservationRepository.saveAndFlush(
        newReservation
      );
      return ReservationBuilder.ReservationToReservationResponse(
        savedReservation
      );
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    } catch (DataIntegrityViolationException e) {
      e.printStackTrace();
    }
    return null;
  }
}
