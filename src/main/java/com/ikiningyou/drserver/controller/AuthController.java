package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardRequest;
import com.ikiningyou.drserver.model.dto.auth.AuthAuthorizeCardResponse;
import com.ikiningyou.drserver.model.dto.auth.web.AuthLoginRequest;
import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.service.CardService;
import com.ikiningyou.drserver.util.Strings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthLoginRequest request) {
    log.info("/auth/login username is  {}, password is {}", request.getUsername(), request.getPassword());

    HttpStatus status = HttpStatus.OK;
    String token = "";
    try {
      token = authService.login(request.getUsername(), request.getPassword());
    } catch (UsernameNotFoundException e) {
      log.info("UsernameNotFoundException");
      status = HttpStatus.UNAUTHORIZED;
    } catch (BadCredentialsException e) {
      log.info("BadCredentialsException");
      status = HttpStatus.UNAUTHORIZED;
    }

    return ResponseEntity.status(status).body(token);
  }

  @GetMapping("/login/success")
  public ResponseEntity<String> loginSuccess(
    HttpServletRequest req,
    HttpServletResponse res,
    Authentication authentication
  ) {
    HttpStatus status = HttpStatus.OK;
    log.info(
      "/login/success res {} res {} isAuth null? {}  context {}",
      req.getHeader("cookie"),
      res.getHeader("cookie"),
      authentication == null,
      SecurityContextHolder.getContext().getAuthentication().getName()
    );

    // if (authentication.isAuthenticated()) {
    //   log.info("authed {}", authentication.getName());
    // } else {
    //   log.info("not authed");
    // }
    return ResponseEntity.status(status).body("login");
  }

  //redirect 되는 과정에서 세션이 담김
  @GetMapping("/logout/success")
  public ResponseEntity<String> logoutSuccess() {
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body("logout");
  }
}
