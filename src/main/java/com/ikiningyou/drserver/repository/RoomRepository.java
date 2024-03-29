package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
  @Transactional
  @Modifying
  @Query("delete from Room where id in :ids")
  void deleteAllByIdInQuery(@Param("ids") List<Integer> ids);

  List<Room> findByAddressContaining(String address);
}
