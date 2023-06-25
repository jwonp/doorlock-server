package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserAddRequest;
import com.ikiningyou.drserver.model.dto.user.UserResponse;
import com.ikiningyou.drserver.repository.UserRepository;
import com.ikiningyou.drserver.util.builder.user.UserBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailService userDetailService;

  public UserResponse getUserById(String userId) {
    Optional<User> rowUser = userRepository.findById(userId);
    if (rowUser.isPresent() == false) {
      return null;
    }
    User user = rowUser.get();

    return UserBuilder.UserToUserResponse(user);
  }

  public UserResponse[] getAllUserList() {
    List<User> allUserList = userRepository.findAll();
    List<UserResponse> userList = new ArrayList<UserResponse>();
    for (User user : allUserList) {
      userList.add(UserBuilder.UserToUserResponse(user));
    }

    return userList.toArray(new UserResponse[userList.size()]);
  }

  public UserResponse addUser(UserAddRequest newUser) {
    User user = User
      .builder()
      .id(newUser.getId())
      .name(newUser.getName())
      .phone(newUser.getPhone())
      .build();

    try {
      User savedUser = userRepository.save(user);
      userDetailService.addUserDetail(newUser.getId(), newUser.getPassword());
      return UserBuilder.UserToUserResponse(savedUser);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Transactional
  public boolean updateLastTaggedTime(String userId, Date lastTaggedTime) {
    Optional<User> rowUser = userRepository.findById(userId);
    if (rowUser.isPresent() == false) {
      return false;
    }
    User user = rowUser.get();
    user.setLastTagged(lastTaggedTime);
    return true;
  }
}
