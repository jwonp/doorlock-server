package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.CardWithReservationOnIndex;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CardRepository extends JpaRepository<Card, String> {
  @Transactional
  @Modifying
  @Query("delete from Card where id in :ids")
  void deleteAllByIdInQuery(@Param("ids") List<String> ids);

  List<Card> findByIdContaining(String id);

  @Query(
    value = "SELECT B.card_id as cardId, " +
    "B.last_tagged as lastTagged, " +
    "A.reservation_id as reservationId, " +
    "C.user_id as userId, " +
    "C.user_name as name, " +
    "C.phone as phone, " +
    "D.address as address, " +
    "E.requested_time as lostTime " +
    "FROM reservation A " +
    "LEFT JOIN card B ON A.card_id = B.card_id " +
    "LEFT JOIN user C ON A.user_id = C.user_id " +
    "LEFT JOIN room D ON A.room_id = D.room_id " +
    "LEFT JOIN lostcard E ON A.card_id = E.card_id " +
    "WHERE A.user_id = :userId",
    nativeQuery = true
  )
  List<CardWithReservationOnIndex> getAllByUserId(
    @Param("userId") String userId
  );
}
