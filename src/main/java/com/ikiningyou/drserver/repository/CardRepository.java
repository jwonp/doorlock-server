package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.CardListResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, String> {
  @Query(
    value = "SELECT" +
    " A.id, A.maxSize, A.type," +
    " A.isIsoDep," +
    " A.isNfcA, A.isNfcB, A.isNfcF, A.isNfcV," +
    " A.isNdef, A.isNdefFormatable," +
    " A.isMifareClassic, A.isMifareUltralight," +
    " A.isUsed," +
    " B.id, B.roomId" +
    " FROM Card A LEFT JOIN User B ON A.id = B.cardId"
  )
  Optional<List<CardListResponse>> getCardListWithUserIdAndRoomId();
}
