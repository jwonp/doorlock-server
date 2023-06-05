package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.util.RSACrypto;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/crypto")
public class CryptoController {

  @Autowired
  RSACrypto rsaCrypto;

  @GetMapping("/test")
  public ResponseEntity<String> testCrypto(
    @RequestParam("plain") String plainText
  ) {
    HashMap<String, String> keyPair = rsaCrypto.getKeyPairAsString();

    String encryptText = rsaCrypto.encrypt(
      plainText,
      rsaCrypto.getPublicKey(keyPair)
    );
    log.info("encryptText is {}", encryptText);
    String decryptText = rsaCrypto.decrypt(
      encryptText,
      rsaCrypto.getPrivateKey(keyPair)
    );
    log.info("decryptText is {}", decryptText);
    return ResponseEntity.ok().body(decryptText);
  }

  @GetMapping("/privatekey")
  public ResponseEntity<Boolean> testRefreshPrivateKey() {
    HashMap<String, String> keyPair = rsaCrypto.getKeyPairAsString();
    
    return ResponseEntity.ok().body(true);
  }
}
