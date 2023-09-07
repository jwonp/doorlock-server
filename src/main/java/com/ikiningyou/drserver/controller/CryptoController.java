package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.util.RSACrypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/crypto")
public class CryptoController {

  @Autowired
  RSACrypto rsaCrypto;

  @GetMapping("/test")
  public ResponseEntity<String> testCrypto(
    @RequestParam("plain") String plainText
  ) {
    // HashMap<String, String> keyPair = rsaCrypto.getKeyPairAsString();

    // String encryptText = rsaCrypto.encrypt(
    //   plainText,
    //   rsaCrypto.getPublicKey(keyPair)
    // );
    // log.info("encryptText is {}", encryptText);
    // String decryptText = rsaCrypto.decrypt(
    //   encryptText,
    //   rsaCrypto.getPrivateKey(keyPair)
    // );
    // log.info("decryptText is {}", decryptText);
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/privatekey")
  public ResponseEntity<Boolean> testRefreshPrivateKey() {
    return ResponseEntity.ok().body(true);
  }
}
