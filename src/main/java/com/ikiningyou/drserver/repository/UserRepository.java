package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserListWithReservationResponse;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, String> {
  @Query(
    "SELECT " +
    "A.id, A.name, A.phone, A.lastTagged, B.cardId, B.roomId " +
    "FROM User A LEFT JOIN Reservation B ON A.id = B.userId"
  )
  Optional<List<UserListWithReservationResponse>> getUserListWithReservataion();

}
