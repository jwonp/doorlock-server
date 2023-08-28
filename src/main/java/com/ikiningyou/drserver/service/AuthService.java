package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.config.EncoderConfig;
import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.LogRepository;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import com.ikiningyou.drserver.util.JwtUtil;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

  @Autowired
  private LogRepository logRepository;

  @Autowired
  private UserDetailRepository userDetailRepository;

  @Autowired
  private EncoderConfig encoderConfig;

  private Long expireTimeMs = 1000 * 60 * 60l;

  @Value("${jwt.token.secret}")
  private String key;

  public void addTagLog(String cardId, String result, String address) {
    TagLog log = TagLog
      .builder()
      .cardId(cardId)
      .address(address)
      .result(result)
      .build();
    try {
      logRepository.save(log);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
  }

  public String login(String username, String password)
    throws UsernameNotFoundException, BadCredentialsException {
    Supplier<UsernameNotFoundException> s = () ->
      new UsernameNotFoundException("로그인 과정에 오류가 발생했습니다.");
    UserDetail user = userDetailRepository
      .findUserDetailByUsername(username)
      .orElseThrow(s);
    log.info("{} {}", password, user.getPassword());
    if (
      encoderConfig
        .bCryptPasswordEncoder()
        .matches(password, user.getPassword()) ==
      false
    ) {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }
    String token = JwtUtil.createToken(user.getUsername(), key, expireTimeMs);

    return token;
  }
}
