package com.ikiningyou.drserver.model.dto.user;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserUpdateLastTaggedResponse {

  private String userId;
  private Date lastTagged;
}
