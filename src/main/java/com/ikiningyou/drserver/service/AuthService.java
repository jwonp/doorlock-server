package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.config.EncoderConfig;
import com.ikiningyou.drserver.model.dao.Authority;
import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.AuthorityRepository;
import com.ikiningyou.drserver.repository.LogRepository;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import com.ikiningyou.drserver.repository.UserRepository;
import com.ikiningyou.drserver.util.Authorities;
import com.ikiningyou.drserver.util.CardResults;
import com.ikiningyou.drserver.util.JwtUtil;
import java.util.Iterator;
import java.util.List;
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
  private UserRepository userRepository;

  @Autowired
  private UserDetailRepository userDetailRepository;

  @Autowired
  private EncoderConfig encoderConfig;

  private Long expireTimeMs = 1000 * 60 * 60l;

  @Value("${jwt.token.secret}")
  private String key;

  public void addTagLog(String cardId, String result, String address) {
    String tagResult = result;
    if (result.split(".").length > 2) {
      tagResult = CardResults.CARD_ADMIN;
    }
    TagLog log = TagLog
      .builder()
      .cardId(cardId)
      .address(address)
      .result(tagResult)
      .build();
    try {
      logRepository.save(log);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
  }

  public String login(String userId, String password)
    throws UsernameNotFoundException, BadCredentialsException {
    Supplier<UsernameNotFoundException> s = () ->
      new UsernameNotFoundException("로그인 과정에 오류가 발생했습니다.");
    UserDetail userDetail = userDetailRepository
      .findById(userId)
      .orElseThrow(s);
    log.info("{} {}", password, userDetail.getPassword());
    if (
      encoderConfig
        .bCryptPasswordEncoder()
        .matches(password, userDetail.getPassword()) ==
      false
    ) {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }
    User user = userRepository
      .findById(userId)
      .orElseThrow(() ->
        new UsernameNotFoundException("해당 아이디가 존재하지 않습니다.")
      );
    String token = JwtUtil.createToken(
      user.getId(),
      user.getName(),
      key,
      expireTimeMs
    );
    log.info("created token {}", token);
    return token;
  }

  public boolean isAdmin(String username) {
    UserDetail userDetail = userDetailRepository
      .findById(username)
      .orElseThrow(() ->
        new UsernameNotFoundException("해당 유저를 찾을 수 없습니다")
      );

    boolean isAdmin =
      userDetail
        .getAuthorityList()
        .stream()
        .filter(item -> item.getAuthority().equals(Authorities.ADMIN))
        .toArray(String[]::new)
        .length >
      0;

    return isAdmin;
  }
}
