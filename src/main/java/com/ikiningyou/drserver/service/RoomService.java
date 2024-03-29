package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomResponse;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomWithReservation;
import com.ikiningyou.drserver.model.dto.room.web.RoomAdminResponse;
import com.ikiningyou.drserver.model.dto.room.web.RoomSelectResponse;
import com.ikiningyou.drserver.repository.RoomRepository;
import com.ikiningyou.drserver.util.builder.room.RoomBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  @Autowired
  RoomRepository roomRepository;

  public RoomResponse getRoomById(int roomId) {
    Optional<Room> rowRoom = roomRepository.findById(roomId);
    if (rowRoom.isPresent() == false) {
      return null;
    }
    Room room = rowRoom.get();
    return RoomBuilder.RoomToRoomResponse(room);
  }

  public RoomResponse[] getAllRooms() {
    List<Room> rooms = roomRepository.findAll();
    List<RoomResponse> roomList = new ArrayList<RoomResponse>();
    for (Room room : rooms) {
      roomList.add(RoomBuilder.RoomToRoomResponse(room));
    }
    return roomList.toArray(new RoomResponse[roomList.size()]);
  }

  public RoomWithReservation[] getRoomListWithReservation() {
    List<Room> rooms = roomRepository.findAll();
    List<RoomWithReservation> roomList = new ArrayList<RoomWithReservation>();
    for (Room room : rooms) {
      roomList.add(RoomBuilder.RoomToRoomWithReservation(room));
    }
    return roomList.toArray(new RoomWithReservation[roomList.size()]);
  }

  public RoomResponse addRoom(String address) {
    Room newRoom = Room.builder().address(address).build();
    try {
      Room savedRoom = roomRepository.save(newRoom);
      return RoomBuilder.RoomToRoomResponse(savedRoom);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Boolean deleteRooms(Integer[] roomIdList) {
    try {
      roomRepository.deleteAllByIdInQuery(Arrays.asList(roomIdList));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public RoomWithReservation[] searchRoomByAddress(String address) {
    List<Room> searchedRooms = roomRepository.findByAddressContaining(address);
    List<RoomWithReservation> roomList = new ArrayList<RoomWithReservation>();
    for (Room room : searchedRooms) {
      roomList.add(RoomBuilder.RoomToRoomWithReservation(room));
    }
    return roomList.toArray(new RoomWithReservation[roomList.size()]);
  }

  public RoomAdminResponse[] getAdminCards() {
    List<Room> rooms = roomRepository.findAll();
    return rooms
      .stream()
      .map(item -> RoomBuilder.RoomToRoomAdminResponse(item))
      .toArray(RoomAdminResponse[]::new);
  }

  public RoomSelectResponse[] getRoomListForRequest() {
    List<Room> rooms = roomRepository.findAll();

    return rooms
      .stream()
      .map(item -> RoomBuilder.RoomToRoomSelectResponse(item))
      .toArray(RoomSelectResponse[]::new);
  }
}
