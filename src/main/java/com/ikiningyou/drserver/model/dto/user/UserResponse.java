package com.ikiningyou.drserver.model.dto.user;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserResponse {

  private String id;
  private String name;
  private String phone;
}
