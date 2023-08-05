package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

  @Autowired
  private UserDetailRepository userDetailRepository;

  public UserDetail addUserDetail(String username, String password) {
    UserDetail userDetail = UserDetail
      .builder()
      .username(username)
      .password(password)
      .role("USER")
      .build();
    try {
      UserDetail savedUserDetail = userDetailRepository.save(userDetail);
      return savedUserDetail;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
