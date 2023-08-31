package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.LostCard;
import com.ikiningyou.drserver.model.dto.card.mobile.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.mobile.CardDeleteRequest;
import com.ikiningyou.drserver.model.dto.card.mobile.CardResponse;
import com.ikiningyou.drserver.model.dto.card.mobile.CardWithReservationResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardAdminDetailResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardIndexResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardAdminResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardListResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardRequest;
import com.ikiningyou.drserver.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
  public ResponseEntity<Boolean> requestLostCard(
    @RequestBody LostCardRequest request
  ) {
    Boolean isSaved = cardService.addLostCard(request.getCardId());
    HttpStatus status = HttpStatus.OK;
    if (isSaved != true) {
      status = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(status).body(isSaved);
  }

  @DeleteMapping("/lost")
  public ResponseEntity<Boolean> cancelLostCard(
    @RequestBody LostCardRequest request
  ) {
    log.info("delete lost");
    boolean isCanceled = cardService.cancelLostCard(request.getCardId());
    HttpStatus status = HttpStatus.OK;
    if (isCanceled == false) {
      status = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(status).body(isCanceled);
  }

  @GetMapping("/index")
  public ResponseEntity<CardIndexResponse[]> getCardWithReservationOnIndexByUserId(
    @RequestParam("id") String userId
  ) {
    CardIndexResponse[] cardList = cardService.getCardIndexByUserId(userId);
    HttpStatus status = HttpStatus.OK;

    return ResponseEntity.status(status).body(cardList);
  }

  @GetMapping("/admin/lost")
  public ResponseEntity<LostCardAdminResponse[]> getAdminLostCards() {
    LostCardAdminResponse[] lostCards = cardService.getAdminLostCards();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(lostCards);
  }

  @GetMapping("/admin")
  public ResponseEntity<CardAdminDetailResponse[]> getAdminCards() {
    CardAdminDetailResponse[] cards = cardService.getAdminCards();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(cards);
  }
}
