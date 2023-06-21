package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserAddRequest;
import com.ikiningyou.drserver.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public Boolean modifyCardIdInUser(String userId, String cardId) {
    Optional<User> rowUser =  userRepository.findById(userId);
    if(rowUser.isPresent() == false){
      return false;
    }

    User user = rowUser.get();
    user.setCardId(cardId);

    return true;
  }
  @Transactional
  public Boolean modifyRoomIdInUser(String userId, int roomId) {
    Optional<User> rowUser =  userRepository.findById(userId);
    if(rowUser.isPresent() == false){
      return false;
    }

    User user = rowUser.get();
    user.setRoomId(userId);

    return true;
  }
}
