package com.ikiningyou.drserver.model.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserAddRequest {

  private String id;
  private String password;
  private String name;
  private String phone;
}
