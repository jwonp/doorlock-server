package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.UserDetail;
import com.ikiningyou.drserver.repository.AuthorityRepository;
import com.ikiningyou.drserver.repository.UserDetailRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService {

  @Autowired
  private UserDetailRepository userDetailRepository;

  
  public UserDetail addUserDetail(String username, String password) {
    UserDetail userDetail = UserDetail
      .builder()
      .username(username)
      .password(password)
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

  @Transactional
  public boolean modifyPassword(String id, String password) {
    Optional<UserDetail> rowUserdetail = userDetailRepository.findById(id);
    if (rowUserdetail.isPresent() == false) {
      return false;
    }
    UserDetail userdetail = rowUserdetail.get();
    userdetail.setPassword(password);
    return true;
  }
}
