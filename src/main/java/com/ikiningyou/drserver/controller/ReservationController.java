package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dto.reservation.ReservationAddRequest;
import com.ikiningyou.drserver.model.dto.reservation.ReservationCheckInRequest;
import com.ikiningyou.drserver.model.dto.reservation.ReservationDeleteRequest;
import com.ikiningyou.drserver.model.dto.reservation.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationModifyRequest;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationWithUserResponse;
import com.ikiningyou.drserver.service.ReservationService;
import com.ikiningyou.drserver.util.builder.reservation.ReservationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/list")
  public ResponseEntity<ReservationResponse[]> getAllReservations() {
    ReservationResponse[] reservationList = reservationService.getAllReservations();
    HttpStatus statusCode = HttpStatus.OK;
    if (reservationList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(reservationList);
  }

  @GetMapping("/list/user")
  public ResponseEntity<ReservationWithUserResponse[]> getAllReservationWithUser() {
    ReservationWithUserResponse[] reservationList = reservationService.getAllReservationWithUser();
    HttpStatus statusCode = HttpStatus.OK;
    if (reservationList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(reservationList);
  }

  @GetMapping("/list/full")
  public ResponseEntity<ReservationFullResponse[]> getFullReservations() {
    ReservationFullResponse[] reservationList = reservationService.getFullReservations();
    HttpStatus statusCode = HttpStatus.OK;
    if (reservationList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }

    return ResponseEntity.status(statusCode).body(reservationList);
  }

  @GetMapping("/full")
  public ResponseEntity<ReservationFullResponse> getFullReservation(
    @RequestParam("id") Long reservationId
  ) {
    ReservationFullResponse reservation = reservationService.getFullReservation(
      reservationId
    );
    HttpStatus statusCode = HttpStatus.OK;
    if (reservation == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }

    return ResponseEntity.status(statusCode).body(reservation);
  }

  @PostMapping
  public ResponseEntity<ReservationResponse> addReservation(
    @RequestBody ReservationAddRequest reservation
  ) {
    ReservationResponse savedReservation = reservationService.addReservation(
      reservation.getUserId(),
      reservation.getRoomId(),
      reservation.getCardId()
    );
    HttpStatus statusCode = HttpStatus.OK;
    if (savedReservation == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(savedReservation);
  }

  @PatchMapping
  public ResponseEntity<ReservationResponse> modifyReservation(
    @RequestParam("id") Long id,
    @RequestBody ReservationModifyRequest modifyRequest
  ) throws Exception {
    Reservation modifiedReservation = reservationService.modifyReservation(
      id,
      modifyRequest
    );

    HttpStatus statusCode = HttpStatus.OK;
    if (modifiedReservation == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    ReservationResponse modifiedReservationResponse = ReservationBuilder.ReservationToReservationResponse(
      modifiedReservation
    );
    return ResponseEntity.status(statusCode).body(modifiedReservationResponse);
  }

  @PatchMapping("/checkin")
  public ResponseEntity<Boolean> setCheckIn(
    @RequestBody ReservationCheckInRequest request
  ) {
    Boolean isChanged = reservationService.setCheckIn(
      request.getReservationId(),
      request.isCheckIn()
    );
    HttpStatus status = HttpStatus.OK;
    if (isChanged == false) {
      status = HttpStatus.NOT_ACCEPTABLE;
    }
    return ResponseEntity.status(status).body(isChanged);
  }

  @DeleteMapping
  public ResponseEntity<Boolean> deleteReservations(
    @RequestBody ReservationDeleteRequest request
  ) {
    Boolean isDeleted = reservationService.deleteReservations(
      request.getIdList()
    );
    HttpStatus statusCode = HttpStatus.OK;
    if (isDeleted == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(isDeleted);
  }
}
