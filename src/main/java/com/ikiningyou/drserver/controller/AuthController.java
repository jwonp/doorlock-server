package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardRequest;
import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardResponse;
import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.service.CardService;
import com.ikiningyou.drserver.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/card")
  public ResponseEntity<AuthAuthorizeCardResponse> authorizeTaggedCard(
    @RequestBody AuthAuthorizeCardRequest request
  ) {
    String AuthorizedResult = cardService.authorizeCard(request.getCardId());
    HttpStatus status = HttpStatus.OK;
    if (AuthorizedResult == Strings.CARD_LOST) {
      status = HttpStatus.UNAUTHORIZED;
    } 
    AuthAuthorizeCardResponse result = AuthAuthorizeCardResponse
      .builder()
      .result(AuthorizedResult)
      .build();
      
    authService.addTagLog(
      request.getCardId(),
      result.getResult(),
      request.getAddress()
    );

    return ResponseEntity.status(status).body(result);
  }
}
