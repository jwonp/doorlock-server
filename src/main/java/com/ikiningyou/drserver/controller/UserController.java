package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardAdminResponse;
import com.ikiningyou.drserver.model.dto.user.mobile.UserAddRequest;
import com.ikiningyou.drserver.model.dto.user.mobile.UserDeleteRequest;
import com.ikiningyou.drserver.model.dto.user.mobile.UserModifyRequest;
import com.ikiningyou.drserver.model.dto.user.mobile.UserResponse;
import com.ikiningyou.drserver.model.dto.user.mobile.UserWithReservationsResponse;
import com.ikiningyou.drserver.model.dto.user.web.UserAdminResponse;
import com.ikiningyou.drserver.service.UserDetailService;
import com.ikiningyou.drserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserDetailService userDetailService;

  @GetMapping
  public ResponseEntity<UserResponse> getUserById(
    @RequestParam("id") String userId
  ) {
    UserResponse user = userService.getUserById(userId);
    HttpStatus statusCode = HttpStatus.OK;
    if (user == null) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(user);
  }

  @GetMapping("/list")
  public ResponseEntity<UserResponse[]> getAllUserList() {
    UserResponse[] userList = userService.getAllUserList();
    HttpStatus statusCode = HttpStatus.OK;
    if (userList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(userList);
  }

  @GetMapping("/list/reservation")
  public ResponseEntity<UserWithReservationsResponse[]> getUserListWithReservations() {
    UserWithReservationsResponse[] userList = userService.getUserListWithReservations();
    HttpStatus statusCode = HttpStatus.OK;
    if (userList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(userList);
  }

  @GetMapping("/search")
  public ResponseEntity<UserWithReservationsResponse[]> searchUserById(
    @RequestParam("id") String id
  ) {
    UserWithReservationsResponse[] userList = userService.searchUserById(id);
    HttpStatus statusCode = HttpStatus.OK;
    if (userList.length == 0) {
      statusCode = HttpStatus.NO_CONTENT;
    }
    return ResponseEntity.status(statusCode).body(userList);
  }

  @PostMapping
  public ResponseEntity<UserResponse> addUser(
    @RequestBody UserAddRequest user
  ) {
    UserResponse savedUser = userService.addUser(user);
    HttpStatus statusCode = HttpStatus.OK;
    if (savedUser == null) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(savedUser);
  }

  @PatchMapping
  public ResponseEntity<Boolean> modifyUser(
    @RequestBody UserModifyRequest request
  ) {
    String userId = request.getId();
    HttpStatus status = HttpStatus.OK;

    boolean isPasswordModified = true;
    boolean isNameModified = true;
    boolean isPhoneModified = true;

    boolean isSuccess = true;
    if (request.getPassword().isPresent()) {
      String password = request.getPassword().get();
      isPasswordModified = userDetailService.modifyPassword(userId, password);
    }
    if (request.getName().isPresent()) {
      String name = request.getName().get();
      isNameModified = userService.modifyUserName(userId, name);
    }
    if (request.getPhone().isPresent()) {
      String phone = request.getPhone().get();
      isPhoneModified = userService.modifyUserPhone(userId, phone);
    }
    if (!isPasswordModified || !isNameModified || !isPhoneModified) {
      status = HttpStatus.BAD_REQUEST;
      isSuccess = false;
    }
    return ResponseEntity.status(status).body(isSuccess);
  }

  @DeleteMapping
  public ResponseEntity<Boolean> deleteUsers(
    @RequestBody UserDeleteRequest request
  ) {
    Boolean isDeleted = userService.deleteUsers(request.getIdList());
    HttpStatus statusCode = HttpStatus.OK;
    if (isDeleted == false) {
      statusCode = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntity.status(statusCode).body(isDeleted);
  }

  @GetMapping("/admin")
  public ResponseEntity<UserAdminResponse[]> getAdminUsers() {
    UserAdminResponse[] users = userService.getAdminUsers();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(users);
  }
}
