package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dto.reservation.ReservationWithUserResponse;
import com.ikiningyou.drserver.model.dto.user.UserAddRequest;
import com.ikiningyou.drserver.model.dto.user.UserDeleteRequest;
import com.ikiningyou.drserver.model.dto.user.UserResponse;
import com.ikiningyou.drserver.model.dto.user.UserUpdateLastTaggedResponse;
import com.ikiningyou.drserver.model.dto.user.UserWithReservationsResponse;
import com.ikiningyou.drserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

  @PatchMapping("/tag/last")
  public ResponseEntity<Boolean> updateLastTaggedTime(
    @RequestBody UserUpdateLastTaggedResponse response
  ) {
    boolean isUpdated = userService.updateLastTaggedTime(
      response.getUserId(),
      response.getLastTagged()
    );
    HttpStatusCode statusCode = HttpStatus.OK;
    if (isUpdated == false) {
      statusCode = HttpStatus.NOT_MODIFIED;
    }
    return ResponseEntity.status(statusCode).body(isUpdated);
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
}
