package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.UserAddRequest;
import com.ikiningyou.drserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<User[]> getAllUserList() {
    User[] userList = userService.getAllUserList();
    return ResponseEntity.status(200).body(userList);
  }

  @PostMapping
  public ResponseEntity<Boolean> addUser(@RequestBody UserAddRequest user) {
    userService.addUser(user);
    return ResponseEntity.status(200).body(true);
  }
}
