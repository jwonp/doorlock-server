package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.card.CardListWithUserAndRoom;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, String> {
  @Query(
    value = "SELECT" +
    " A.id as id, A.maxSize as maxSize, A.type as type," +
    " A.isIsoDep as isIsoDep," +
    " A.isNfcA as isNfcA, A.isNfcB as isNfcB, A.isNfcF as isNfcF, A.isNfcV as isNfcV," +
    " A.isNdef as isNdef, A.isNdefFormatable as isNdefFormatable," +
    " A.isMifareClassic as isMifareClassic, A.isMifareUltralight as isMifareUltralight," +
    " A.isUsed as isUsed," +
    " B.id as userId, B.roomId as roomId" +
    " FROM Card A LEFT JOIN User B ON A.id = B.cardId"
  )
  Optional<List<CardListWithUserAndRoom>> getCardListWithUserIdAndRoomId();
}
