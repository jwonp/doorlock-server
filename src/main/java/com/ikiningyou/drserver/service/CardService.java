package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.TechType;
import com.ikiningyou.drserver.model.dto.card.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.CardResponse;
import com.ikiningyou.drserver.model.dto.card.CardWithReservationResponse;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.util.builder.card.CardBuilder;
import com.ikiningyou.drserver.util.builder.card.TechTypeBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  @Autowired
  private CardRepository cardRepository;

  public CardResponse getCardById(String userId) {
    Optional<Card> rowCard = cardRepository.findById(userId);
    if (rowCard.isPresent() == false) {
      return null;
    }
    Card card = rowCard.get();

    return CardBuilder.CardToCardResponse(card);
  }

  public CardWithReservationResponse[] getCardListWithReservation() {
    List<Card> cards = cardRepository.findAll();
    List<CardWithReservationResponse> cardList = new ArrayList<CardWithReservationResponse>();
    for (Card card : cards) {
      cardList.add(CardBuilder.CardToCardWithReservationResponse(card));
    }

    return cardList.toArray(new CardWithReservationResponse[cardList.size()]);
  }

  public CardResponse[] getAllCards() {
    List<Card> cardList = cardRepository.findAll();
    List<CardResponse> cardArray = new ArrayList<CardResponse>();

    for (Card card : cardList) {
      TechType techType = TechTypeBuilder.CardtoTechType(card);
      cardArray.add(
        CardResponse
          .builder()
          .id(card.getId())
          .maxSize(card.getMaxSize())
          .type(card.getType())
          .techType(techType)
          .build()
      );
    }
    return cardArray.toArray(new CardResponse[cardArray.size()]);
  }

  public CardResponse addCard(CardAddRequest newCard) {
    if (cardRepository.findById(newCard.getId()).isPresent()) {
      return null;
    }

    Card card = CardBuilder.CardAddRequestToCard(newCard);
    try {
      Card savedCard = cardRepository.save(card);
      return CardBuilder.CardToCardResponse(savedCard);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
