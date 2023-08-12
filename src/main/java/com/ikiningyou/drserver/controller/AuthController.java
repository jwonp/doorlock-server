package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardResponse;
import com.ikiningyou.drserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private CardService cardService;

  @GetMapping("/card")
  public ResponseEntity<AuthAuthorizeCardResponse> authorizeTaggedCard(
    @RequestParam("id") String cardId
  ) {
    AuthAuthorizeCardResponse result = AuthAuthorizeCardResponse
      .builder()
      .result(cardService.authorizeCard(cardId))
      .build();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(result);
  }
}
