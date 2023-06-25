package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.room.RoomAddRequest;
import com.ikiningyou.drserver.model.dto.room.RoomResponse;
import com.ikiningyou.drserver.service.RoomService;
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
@RequestMapping("/room")
public class RoomController {

  @Autowired
  RoomService roomService;

  @GetMapping
  public ResponseEntity<RoomResponse> getRoomById(@RequestParam("id") int roomId) {
    RoomResponse room = roomService.getRoomById(roomId);
    HttpStatus statusCode = HttpStatus.OK;
    if (room == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(room);
  }

  @GetMapping("/list")
  public ResponseEntity<RoomResponse[]> getAllRooms() {
    RoomResponse[] rooms = roomService.getAllRooms();
    HttpStatus statusCode = HttpStatus.OK;
    if (rooms.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(rooms);
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
}
