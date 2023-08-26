package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.auth.PrincipalDetailsService;
import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardRequest;
import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardResponse;
import com.ikiningyou.drserver.model.dto.auth.web.AuthLoginRequest;
import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.service.CardService;
import com.ikiningyou.drserver.util.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private CardService cardService;

  @Autowired
  private AuthService authService;

  @Autowired
  private PrincipalDetailsService loginService;

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

  // @PostMapping("/login")
  // public ResponseEntity<String> login(@RequestBody AuthLoginRequest request) {
  //   log.info(
  //     "controller id {} pwd {}",
  //     request.getUsername(),
  //     request.getPassword()
  //   );
  //   loginService.loadUserByUsername(request.getUsername());
  //   HttpStatus status = HttpStatus.OK;
  //   return ResponseEntity.status(status).body(null);
  // }

  @GetMapping("/login/success")
  public ResponseEntity<String> loginSuccess() {
    HttpStatus status = HttpStatus.OK;
    log.info("/login/success");
    return ResponseEntity.status(status).body("login");
  }

  @GetMapping("/logout/success")
  public ResponseEntity<String> logoutSuccess() {
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body("logout");
  }
}
