package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationAddRequest;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationDeleteRequest;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationModifyRequest;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationWithUserResponse;
import com.ikiningyou.drserver.model.dto.reservation.web.ReservationAdminResponse;
import com.ikiningyou.drserver.model.dto.reservation.web.ReservationWithProfile;
import com.ikiningyou.drserver.model.dto.reservedRequest.web.AdminCancelRequest;
import com.ikiningyou.drserver.model.dto.reservedRequest.web.AdminReservedRequestResponse;
import com.ikiningyou.drserver.model.dto.reservedRequest.web.ReservationChangeRequest;
import com.ikiningyou.drserver.service.ReservationService;
import com.ikiningyou.drserver.util.builder.reservation.ReservationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

@Slf4j
@RestController
@RequestMapping("/reservation")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping
  public ResponseEntity<ReservationWithProfile> getReservationWithProfileById(
    @RequestParam("id") Long id
  ) throws NotFoundException {
    ReservationWithProfile reservation = reservationService.getReservationWithProfileById(
      id
    );
    HttpStatus status = HttpStatus.OK;

    return ResponseEntity.status(status).body(reservation);
  }

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

  @GetMapping("/admin/reserved")
  public ResponseEntity<AdminReservedRequestResponse[]> getAdminReservedRequests() {
    AdminReservedRequestResponse[] reservations = reservationService.getAdminReservedRequests();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(reservations);
  }

  @DeleteMapping("/admin/reserved")
  public ResponseEntity<Boolean> cancelReservedRequest(
    @RequestBody AdminCancelRequest request
  ) {
    Boolean isDeleted = reservationService.cancelReservedRequest(
      request.getRequestId()
    );
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(isDeleted);
  }

  @GetMapping("/admin")
  public ResponseEntity<ReservationAdminResponse[]> getAdminReservations() {
    ReservationAdminResponse[] reservations = reservationService.getAdminReservations();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(reservations);
  }

  @PostMapping("/request")
  public ResponseEntity<Boolean> requestReservationChanges(
    @RequestBody ReservationChangeRequest request
  ) throws NotFoundException {
    Boolean isSaved = reservationService.requestReservationChange(
      request.getReservationId(),
      request.getRoomId()
    );
    log.info("is saved request {}", isSaved.toString());
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(isSaved);
  }
}
