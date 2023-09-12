package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Authority;
import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.AuthorityRepository;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import com.ikiningyou.drserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private UserDetailRepository userDetailRepository;

  public Authority addAuthority(String userId, String authority) {
    UserDetail userDetail = userDetailRepository
      .findById(userId)
      .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    Authority saveAuthority = Authority
      .builder()
      .userDetail(userDetail)
      .authority(authority)
      .build();
    try {
      return authorityRepository.save(saveAuthority);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
