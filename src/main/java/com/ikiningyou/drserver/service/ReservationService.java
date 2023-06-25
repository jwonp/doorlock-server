package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.repository.ReservationRepository;
import com.ikiningyou.drserver.repository.RoomRepository;
import com.ikiningyou.drserver.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  public Reservation[] getAllReservations() {
    List<Reservation> reservationList = reservationRepository.findAll();
    return reservationList.toArray(new Reservation[reservationList.size()]);
  }

  public Reservation[] getAllReservationWithUser() {
    List<Reservation> reservationList = reservationRepository.findAllReservations();

    return reservationList.toArray(new Reservation[reservationList.size()]);
  }

  public Reservation addReservation(String userId, int roomId, String cardId) {
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
      return savedReservation;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
