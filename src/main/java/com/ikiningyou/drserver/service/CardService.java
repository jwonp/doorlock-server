package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dao.LostCard;
import com.ikiningyou.drserver.model.data.TechType;
import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.CardWithReservationOnIndex;
import com.ikiningyou.drserver.model.dto.card.mobile.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.mobile.CardResponse;
import com.ikiningyou.drserver.model.dto.card.mobile.CardWithReservationResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardAdminDetailResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardIndexResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardAdminResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardListResponse;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.repository.LostCardRepository;
import com.ikiningyou.drserver.util.Authorities;
import com.ikiningyou.drserver.util.CardResults;
import com.ikiningyou.drserver.util.JwtUtil;
import com.ikiningyou.drserver.util.builder.card.CardBuilder;
import com.ikiningyou.drserver.util.builder.card.TechTypeBuilder;
import com.ikiningyou.drserver.util.builder.lostCard.LostCardBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private LostCardRepository lostCardRepository;

  private Long expireTimeMs = 1000 * 60 * 60l;

  @Value("${jwt.token.secret}")
  private String key;

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
          .lastTagged(card.getLastTagged())
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

  public boolean deleteCards(String[] cardIdList) {
    try {
      cardRepository.deleteAllByIdInQuery(Arrays.asList(cardIdList));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Transactional
  public String authorizeCard(String cardId) {
    Optional<LostCard> rowLostCard = lostCardRepository.findById(cardId);
    if (rowLostCard.isPresent()) {
      return CardResults.CARD_LOST;
    }

    Optional<Card> card = cardRepository.findById(cardId);

    if (card.isPresent() == false) {
      return CardResults.CARD_UNAUTHORIZED;
    }
    card.get().setLastTagged(LocalDateTime.now());
    if (card.get().isAdmin()) {
      return JwtUtil.createToken(
        Authorities.ADMIN_CARD,
        Authorities.ADMIN_CARD,
        key,
        expireTimeMs
      );
    }
    return CardResults.CARD_AUTHORIZED;
  }

  public CardWithReservationResponse[] searchCardById(String id) {
    List<Card> searchedCards = cardRepository.findByIdContaining(id);
    List<CardWithReservationResponse> cardList = new ArrayList<CardWithReservationResponse>();
    for (Card card : searchedCards) {
      cardList.add(CardBuilder.CardToCardWithReservationResponse(card));
    }

    return cardList.toArray(new CardWithReservationResponse[cardList.size()]);
  }

  public LostCardListResponse[] getAllLostCards() {
    List<LostCard> lostCards = lostCardRepository.findAll();
    List<LostCardListResponse> lostCardList = new ArrayList<LostCardListResponse>();
    for (LostCard lostCard : lostCards) {
      lostCardList.add(
        LostCardBuilder.LostCardToLostCardListResponse(lostCard)
      );
    }

    return lostCardList.toArray(new LostCardListResponse[lostCardList.size()]);
  }

  public Boolean addLostCard(String lostCardId) {
    Optional<Card> rowLostCard = cardRepository.findById(lostCardId);
    if (rowLostCard.isPresent() == false) {
      return null;
    }
    LostCard lostCard = LostCard.builder().card(rowLostCard.get()).build();
    try {
      lostCardRepository.save(lostCard);
      return true;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean cancelLostCard(String cardId) {
    try {
      lostCardRepository.deleteByIdInQuery(cardId);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public CardIndexResponse[] getCardIndexByUserId(String userId) {
    List<CardWithReservationOnIndex> cards = cardRepository.getAllByUserId(
      userId
    );
    if (cards.size() <= 0) {
      return new CardIndexResponse[0];
    }
    List<CardIndexResponse> cardResponses = new ArrayList<CardIndexResponse>();
    for (CardWithReservationOnIndex card : cards) {
      cardResponses.add(
        CardBuilder.CardWithReservationOnIndexToCardIndexResponse(card)
      );
    }
    CardIndexResponse[] cardList = cardResponses.toArray(
      new CardIndexResponse[cardResponses.size()]
    );

    return cardList;
  }

  public LostCardAdminResponse[] getAdminLostCards() {
    List<LostCard> lostCards = lostCardRepository.findAll();
    return lostCards
      .stream()
      .map(item -> LostCardBuilder.LostCardToLostCardAdminResponse(item))
      .toArray(LostCardAdminResponse[]::new);
  }

  public CardAdminDetailResponse[] getAdminCards() {
    List<Card> cards = cardRepository.findAll();
    return cards
      .stream()
      .map(item -> CardBuilder.CardToCardAdminDetailResponse(item))
      .toArray(CardAdminDetailResponse[]::new);
  }
}
