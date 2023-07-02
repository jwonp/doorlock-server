package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dto.reservation.ReservationAddRequest;
import com.ikiningyou.drserver.model.dto.reservation.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationWithUserResponse;
import com.ikiningyou.drserver.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
