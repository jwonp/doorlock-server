package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserAddRequest;
import com.ikiningyou.drserver.model.dto.user.UserModifyCardRequest;
import com.ikiningyou.drserver.model.dto.user.UserModifyRoomRequest;
import com.ikiningyou.drserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @GetMapping
  public ResponseEntity<User> getUserById(@RequestParam("id") String userId) {
    User user = userService.getUserById(userId);
    int statusCode = 200;
    if (user == null) {
      statusCode = 400;
    }
    return ResponseEntity.status(statusCode).body(user);
  }

  @GetMapping("/list")
  public ResponseEntity<User[]> getAllUserList() {
    User[] userList = userService.getAllUserList();
    return ResponseEntity.status(200).body(userList);
  }

  @PostMapping
  public ResponseEntity<Boolean> addUser(@RequestBody UserAddRequest user) {
    userService.addUser(user);
    return ResponseEntity.status(200).body(true);
  }

  @PatchMapping("/card")
  public ResponseEntity<Boolean> modifyCardIdInUser(
    @RequestBody UserModifyCardRequest userModifyCardRequest
  ) {
    boolean isModified;
    try {
      isModified =
        userService.modifyCardIdInUser(
          userModifyCardRequest.getUserId(),
          userModifyCardRequest.getCardId()
        );
    } catch (Exception e) {
      return ResponseEntity.status(400).body(false);
    }
    return ResponseEntity.status(200).body(isModified);
  }

  @PatchMapping("/room")
  public ResponseEntity<Boolean> modifyRoomIdInUser(
    @RequestBody UserModifyRoomRequest userModifyRoomRequest
  ) {
    boolean isModified;
    try {
      isModified =
        userService.modifyRoomIdInUser(
          userModifyRoomRequest.getUserId(),
          userModifyRoomRequest.getRoomId()
        );
    } catch (Exception e) {
      return ResponseEntity.status(400).body(false);
    }
    return ResponseEntity.status(200).body(isModified);
  }
}
