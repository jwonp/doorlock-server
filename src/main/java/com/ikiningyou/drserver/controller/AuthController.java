package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardRequest;
import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardResponse;
import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private CardService cardService;

  @Autowired
  private AuthService authService;

  @GetMapping("/card")
  public ResponseEntity<AuthAuthorizeCardResponse> authorizeTaggedCard(
    @RequestBody AuthAuthorizeCardRequest request
  ) {
    AuthAuthorizeCardResponse result = AuthAuthorizeCardResponse
      .builder()
      .result(cardService.authorizeCard(request.getCardId()))
      .build();
    authService.addTagLog(
      request.getCardId(),
      result.getResult(),
      request.getAddress()
    );
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(result);
  }
}
