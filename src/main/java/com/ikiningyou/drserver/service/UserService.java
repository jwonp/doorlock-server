package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.UserAddRequest;
import com.ikiningyou.drserver.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User[] getAllUserList() {
    List<User> allUserList = userRepository.findAll();
    return allUserList.toArray(new User[allUserList.size()]);
  }

  public User addUser(UserAddRequest newUser) {
    User user = User
      .builder()
      .id(newUser.getId())
      .name(newUser.getName())
      .phone(newUser.getPhone())
      .build();
    try {
      User savedUser = userRepository.save(user);
      return savedUser;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }
}
