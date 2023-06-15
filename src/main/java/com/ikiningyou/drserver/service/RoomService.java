package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.RoomAddRequest;
import com.ikiningyou.drserver.repository.RoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  @Autowired
  RoomRepository roomRepository;

  public Room[] getAllRooms() {
    List<Room> rooms = roomRepository.findAll();
    return rooms.toArray(new Room[rooms.size()]);
  }

  public Room addRoom(RoomAddRequest room) {
    Room newRoom = Room.builder().address(room.getAddress()).build();
    return roomRepository.save(newRoom);
  }
}
