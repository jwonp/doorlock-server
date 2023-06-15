package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.RoomAddRequest;
import com.ikiningyou.drserver.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {

  @Autowired
  RoomService roomService;

  @GetMapping
  public ResponseEntity<Room[]> getAllRooms() {
    Room[] rooms = roomService.getAllRooms();
    return ResponseEntity.status(200).body(rooms);
  }

  @PostMapping
  public ResponseEntity<Room> addRoom(@RequestBody RoomAddRequest roomAddRequest) {
    Room savedRoom = roomService.addRoom(roomAddRequest);
    return ResponseEntity.status(200).body(null);
  }
}
