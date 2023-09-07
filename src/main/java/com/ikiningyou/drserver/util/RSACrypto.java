package com.ikiningyou.drserver.util;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;
import java.util.Base64;
import java.util.HashMap;
import javax.crypto.Cipher;
import org.springframework.stereotype.Component;

@Component
public class RSACrypto {

  public String createUUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
  

  /*
   * 암호화 : 공개키로 진행
   */
  public String encrypt(String plainText, String stringPublicKey) {
    String encryptedText = null;

    try {
      // 평문으로 전달받은 공개키를 사용하기 위해 공개키 객체 생성
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      byte[] bytePublicKey = Base64
        .getDecoder()
        .decode(stringPublicKey.getBytes());
      X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
      PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

      // 만들어진 공개키 객체로 암호화 설정
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);

      byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
      encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return encryptedText;
  }

  /*
   * 복호화 : 개인키로 진행
   */
  public String decrypt(String encryptedText, String stringPrivateKey) {
    String decryptedText = null;

    try {
      // 평문으로 전달받은 공개키를 사용하기 위해 공개키 객체 생성
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      byte[] bytePrivateKey = Base64
        .getDecoder()
        .decode(stringPrivateKey.getBytes());
      PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
        bytePrivateKey
      );
      PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

      // 만들어진 공개키 객체로 복호화 설정
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);

      // 암호문을 평문화하는 과정
      byte[] encryptedBytes = Base64
        .getDecoder()
        .decode(encryptedText.getBytes());
      byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      decryptedText = new String(decryptedBytes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return decryptedText;
  }

  public String getPrivateKey(HashMap<String, String> keyPair) {
    return keyPair.get("privateKey");
  }

  public String getPublicKey(HashMap<String, String> keyPair) {
    return keyPair.get("publicKey");
  }

    /*
   * 공개키와 개인키 한 쌍 생성
   */
  private HashMap<String, String> getKeyPairAsString() {
    HashMap<String, String> stringKeypair = new HashMap<>();
    try {
      SecureRandom secureRandom = new SecureRandom();
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048, secureRandom);
      KeyPair keyPair = keyPairGenerator.genKeyPair();

      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      String stringPublicKey = Base64
        .getEncoder()
        .encodeToString(publicKey.getEncoded());
      String stringPrivateKey = Base64
        .getEncoder()
        .encodeToString(privateKey.getEncoded());

      stringKeypair.put("publicKey", stringPublicKey);
      stringKeypair.put("privateKey", stringPrivateKey);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return stringKeypair;
  }
}
