package com.ikiningyou.drserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikiningyou.drserver.model.dao.Room;

public interface RoomRepository extends JpaRepository<Room,String> {
    
}
