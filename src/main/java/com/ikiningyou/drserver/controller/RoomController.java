package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.card.web.CardAdminDetailResponse;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomAddRequest;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomDeleteRequest;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomResponse;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomWithReservation;
import com.ikiningyou.drserver.model.dto.room.web.RoomAdminResponse;
import com.ikiningyou.drserver.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {

  @Autowired
  RoomService roomService;

  @GetMapping
  public ResponseEntity<RoomResponse> getRoomById(
    @RequestParam("id") int roomId
  ) {
    RoomResponse room = roomService.getRoomById(roomId);
    HttpStatus statusCode = HttpStatus.OK;
    if (room == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(room);
  }

  @GetMapping("/list")
  public ResponseEntity<RoomResponse[]> getAllRooms() {
    log.info("room list requested");
    RoomResponse[] rooms = roomService.getAllRooms();
    HttpStatus statusCode = HttpStatus.OK;
    if (rooms.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(rooms);
  }

  @GetMapping("/list/reservation")
  public ResponseEntity<RoomWithReservation[]> getRoomListWithReservation() {
    RoomWithReservation[] roomList = roomService.getRoomListWithReservation();
    HttpStatus statusCode = HttpStatus.OK;
    return ResponseEntity.status(statusCode).body(roomList);
  }

  @GetMapping("/search")
  public ResponseEntity<RoomWithReservation[]> searchRoomByAddress(
    @RequestParam("address") String address
  ) {
    RoomWithReservation[] roomList = roomService.searchRoomByAddress(address);
    HttpStatus statusCode = HttpStatus.OK;
    return ResponseEntity.status(statusCode).body(roomList);
  }

  @PostMapping
  public ResponseEntity<RoomResponse> addRoom(
    @RequestBody RoomAddRequest roomAddRequest
  ) {
    RoomResponse savedRoom = roomService.addRoom(roomAddRequest.getAddress());
    HttpStatus statusCode = HttpStatus.OK;
    if (savedRoom == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(savedRoom);
  }

  @DeleteMapping
  public ResponseEntity<Boolean> deleteRoom(
    @RequestBody RoomDeleteRequest request
  ) {
    Boolean isDeleted = roomService.deleteRooms(request.getIdList());
    HttpStatus statusCode = HttpStatus.OK;
    if (isDeleted == false) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(isDeleted);
  }

  @GetMapping("/admin")
  public ResponseEntity<RoomAdminResponse[]> getAdminCards() {
    RoomAdminResponse[] rooms = roomService.getAdminCards();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(rooms);
  }
}
