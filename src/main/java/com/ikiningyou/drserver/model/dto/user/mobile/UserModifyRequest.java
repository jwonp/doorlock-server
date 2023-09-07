package com.ikiningyou.drserver.model.dto.user.mobile;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserModifyRequest {

  private String id;
  private Optional<String> password;
  private Optional<String> name;
  private Optional<String> phone;
}
