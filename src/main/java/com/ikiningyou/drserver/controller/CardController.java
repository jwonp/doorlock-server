package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.card.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.CardListResponse;
import com.ikiningyou.drserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Card> getCardById(@RequestParam("id") String cardId) {
    Card card = cardService.getCardById(cardId);
    HttpStatus statusCode = HttpStatus.OK;
    if (card == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(card);
  }

  @GetMapping("/list")
  public ResponseEntity<CardListResponse[]> getAllCards() {
    CardListResponse[] cardList = cardService.getAllCards();
    HttpStatus statusCode = HttpStatus.OK;
    if (cardList == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(cardList);
  }

  @PostMapping
  public ResponseEntity<Card> addCard(@RequestBody CardAddRequest card) {
    Card savedCard = cardService.addCard(card);
    HttpStatus statusCode = HttpStatus.OK;
    if (savedCard == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }

    return ResponseEntity.status(statusCode).body(savedCard);
  }
}
