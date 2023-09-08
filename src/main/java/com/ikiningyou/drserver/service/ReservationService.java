package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dao.ReservedRequest;
import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationModifyRequest;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationWithUserResponse;
import com.ikiningyou.drserver.model.dto.reservation.web.ReservationAdminResponse;
import com.ikiningyou.drserver.model.dto.reservation.web.ReservationWithProfile;
import com.ikiningyou.drserver.model.dto.reservedRequest.web.AdminReservedRequestResponse;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.repository.ReservationRepository;
import com.ikiningyou.drserver.repository.ReservedRequestRepository;
import com.ikiningyou.drserver.repository.RoomRepository;
import com.ikiningyou.drserver.repository.UserRepository;
import com.ikiningyou.drserver.util.builder.reservation.ReservationBuilder;
import com.ikiningyou.drserver.util.builder.reservedRequset.ReservedRequestBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private ReservedRequestRepository reservedRequestRepository;

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoomRepository roomRepository;

  public ReservationWithProfile getReservationWithProfileById(Long id)
    throws NotFoundException {
    Reservation reservation = reservationRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException());
    return ReservationBuilder.ReservationToReservationWithProfile(reservation);
  }

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

  public ReservationFullResponse getFullReservation(Long id) {
    Optional<Reservation> rowReservation = reservationRepository.findById(id);
    if (rowReservation.isPresent() == false) {
      return null;
    }
    ReservationFullResponse reservation = ReservationBuilder.ReservationToReservationFullResponse(
      rowReservation.get()
    );

    return reservation;
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

  @Transactional
  public Reservation modifyReservation(Long id, ReservationModifyRequest data)
    throws Exception {
    Optional<Reservation> rowReservation = reservationRepository.findById(id);
    if (rowReservation.isPresent() == false) {
      return null;
    }
    Reservation reservation = rowReservation.get();
    if (data.getUserId() != null) {
      User user = userRepository
        .findById(data.getUserId())
        .orElseThrow(() -> new Exception());

      reservation.setUser(user);
    }
    if (data.getCardId() != null) {
      Card card = cardRepository
        .findById(data.getCardId())
        .orElseThrow(() -> new Exception());

      reservation.setCard(card);
    }
    if (data.getRoomId() != null) {
      Room room = roomRepository
        .findById(data.getRoomId().intValue())
        .orElseThrow(() -> new Exception());

      reservation.setRoom(room);
    }

    return reservation;
  }

  public Boolean deleteReservations(Long[] reservationIdList) {
    try {
      reservationRepository.deleteAllByIdInQuery(
        Arrays.asList(reservationIdList)
      );
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public AdminReservedRequestResponse[] getAdminReservedRequests() {
    List<ReservedRequest> reservations = reservedRequestRepository.findAll();
    return reservations
      .stream()
      .map(item ->
        ReservedRequestBuilder.ReservedRequestToAdminReservedRequestResponse(
          item
        )
      )
      .toArray(AdminReservedRequestResponse[]::new);
  }

  public ReservationAdminResponse[] getAdminReservations() {
    List<Reservation> reservations = reservationRepository.findAll();
    return reservations
      .stream()
      .map(item ->
        ReservationBuilder.ReservationToReservationAdminResponse(item)
      )
      .toArray(ReservationAdminResponse[]::new);
  }

  public Boolean requestReservationChange(Long reservationId, int roomId)
    throws NotFoundException {
    Reservation reservation = reservationRepository
      .findById(reservationId)
      .orElseThrow(() -> new NotFoundException());
    ReservedRequest request = ReservedRequest
      .builder()
      .user(reservation.getUser())
      .room(reservation.getRoom())
      .isAccepted(false)
      .build();
    try {
      reservedRequestRepository.save(request);
      return true;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return false;
  }
}
