package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Authority;
import com.ikiningyou.drserver.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  public Authority addAuthority(String username, String authority) {
    Authority saveAuthority = Authority
      .builder()
      .user(username)
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
