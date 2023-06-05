package com.ikiningyou.drserver.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAddRequest {

  private String id;
  private String name;
  private String phone;
}
