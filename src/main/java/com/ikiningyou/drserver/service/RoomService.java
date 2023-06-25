package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  @Autowired
  RoomRepository roomRepository;

  public Room getRoomById(int roomId) {
    Optional<Room> rowRoom = roomRepository.findById(roomId);
    if (rowRoom.isPresent() == false) {
      return null;
    }
    return rowRoom.get();
  }

  public Room[] getAllRooms() {
    List<Room> rooms = roomRepository.findAll();
    return rooms.toArray(new Room[rooms.size()]);
  }

  public Room addRoom(String address) {
    Room newRoom = Room.builder().address(address).build();
    try {
      Room savedRoom = roomRepository.save(newRoom);
      return savedRoom;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
