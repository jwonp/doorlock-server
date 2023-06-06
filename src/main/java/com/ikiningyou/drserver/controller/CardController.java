package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.CardAddRequest;
import com.ikiningyou.drserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

  @Autowired
  private CardService cardService;

  @GetMapping
  public ResponseEntity<Card[]> getAllCards() {
    Card[] cardList = cardService.getAllCards();
    return ResponseEntity.status(200).body(cardList);
  }

  @PostMapping
  public ResponseEntity<String> addCard(@RequestBody CardAddRequest card) {
    String savedCardId = cardService.addCard(card);
    int statusCode = 200;
    if (savedCardId.isEmpty()) {
      statusCode = 400;
    }

    return ResponseEntity.status(statusCode).body(savedCardId);
  }
}
