package com.ikiningyou.drserver.auth;

import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired
  UserDetailRepository userDetailRepository;

  @Override
  public PrincipalDetails loadUserByUsername(String username) {
    Supplier<UsernameNotFoundException> s = () ->
      new UsernameNotFoundException("로그인 과정에 오류가 발생했습니다.");
    log.info("load user {}", username);
    UserDetail user = userDetailRepository
      .findUserDetailByUsername(username)
      .orElseThrow(s);

    log.info("id {} pwd {}", user.getUsername(), user.getPassword());
    return new PrincipalDetails(user);
  }
}
