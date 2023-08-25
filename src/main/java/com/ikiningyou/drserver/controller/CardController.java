package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.LostCard;
import com.ikiningyou.drserver.model.dto.card.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.CardDeleteRequest;
import com.ikiningyou.drserver.model.dto.card.CardResponse;
import com.ikiningyou.drserver.model.dto.card.CardWithReservationResponse;
import com.ikiningyou.drserver.model.dto.lostCard.LostCardAddRequest;
import com.ikiningyou.drserver.model.dto.lostCard.LostCardListResponse;
import com.ikiningyou.drserver.service.CardService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

  @Autowired
  private CardService cardService;

  @GetMapping
  public ResponseEntity<CardResponse> getCardById(
    @RequestParam("id") String cardId
  ) {
    CardResponse card = cardService.getCardById(cardId);
    HttpStatus statusCode = HttpStatus.OK;
    if (card == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(card);
  }

  @GetMapping("/list")
  public ResponseEntity<CardResponse[]> getAllCards() {
    CardResponse[] cardList = cardService.getAllCards();
    HttpStatus statusCode = HttpStatus.OK;
    if (cardList == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(cardList);
  }

  @GetMapping("/list/reservation")
  public ResponseEntity<CardWithReservationResponse[]> getCardListWithReservation() {
    CardWithReservationResponse[] cardList = cardService.getCardListWithReservation();
    HttpStatus statusCode = HttpStatus.OK;
    if (cardList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(cardList);
  }

  @GetMapping("/search")
  public ResponseEntity<CardWithReservationResponse[]> searchCardById(
    @RequestParam("id") String id
  ) {
    CardWithReservationResponse[] cardList = cardService.searchCardById(id);
    HttpStatus statusCode = HttpStatus.OK;
    if (cardList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(cardList);
  }

  @PostMapping
  public ResponseEntity<CardResponse> addCard(
    @RequestBody CardAddRequest card
  ) {
    CardResponse savedCard = cardService.addCard(card);
    HttpStatus statusCode = HttpStatus.OK;

    if (savedCard == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }

    return ResponseEntity.status(statusCode).body(savedCard);
  }

  @DeleteMapping
  public ResponseEntity<Boolean> deleteCards(
    @RequestBody CardDeleteRequest request
  ) {
    Boolean isDeleted = cardService.deleteCards(request.getIdList());
    HttpStatus statusCode = HttpStatus.OK;
    if (isDeleted == false) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(isDeleted);
  }

  @GetMapping("/lost/list")
  public ResponseEntity<LostCardListResponse[]> getAllLostCards() {
    LostCardListResponse[] lostCardList = cardService.getAllLostCards();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(lostCardList);
  }

  @PostMapping("/lost")
  public ResponseEntity<LostCard> requestLostCard(
    @RequestBody LostCardAddRequest request
  ) {
    LostCard savedLostCard = cardService.addLostCard(request.getCardId());
    HttpStatus status = HttpStatus.OK;
    if (savedLostCard == null) {
      status = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(status).body(savedLostCard);
  }
}
