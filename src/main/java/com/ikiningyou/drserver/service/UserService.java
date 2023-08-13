package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserAddRequest;
import com.ikiningyou.drserver.model.dto.user.UserResponse;
import com.ikiningyou.drserver.model.dto.user.UserWithReservationsResponse;
import com.ikiningyou.drserver.repository.UserRepository;
import com.ikiningyou.drserver.util.builder.user.UserBuilder;
import java.util.ArrayList;
import java.util.Arrays;
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

  public UserWithReservationsResponse[] getUserListWithReservations() {
    List<User> users = userRepository.findAll();
    List<UserWithReservationsResponse> userList = new ArrayList<UserWithReservationsResponse>();

    for (User user : users) {
      userList.add(UserBuilder.UserToUserWithReservationsResponse(user));
    }
    return userList.toArray(new UserWithReservationsResponse[userList.size()]);
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
    if (userRepository.findById(newUser.getId()).isPresent()) {
      return null;
    }
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
  public boolean modifyUserName(String id, String name) {
    Optional<User> rowUser = userRepository.findById(id);
    if (rowUser.isPresent() == false) {
      return false;
    }
    User user = rowUser.get();
    user.setName(name);
    return true;
  }

  @Transactional
  public boolean modifyUserPhone(String id, String phone) {
    Optional<User> rowUser = userRepository.findById(id);
    if (rowUser.isPresent() == false) {
      return false;
    }
    User user = rowUser.get();
    user.setName(phone);
    return true;
  }

  public Boolean deleteUsers(String[] cardIdList) {
    try {
      userRepository.deleteAllByIdInQuery(Arrays.asList(cardIdList));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public UserWithReservationsResponse[] searchUserById(String id) {
    List<User> searchedUsers = userRepository.findByIdContaining(id);
    List<UserWithReservationsResponse> userList = new ArrayList<UserWithReservationsResponse>();

    for (User user : searchedUsers) {
      userList.add(UserBuilder.UserToUserWithReservationsResponse(user));
    }
    return userList.toArray(new UserWithReservationsResponse[userList.size()]);
  }
}
