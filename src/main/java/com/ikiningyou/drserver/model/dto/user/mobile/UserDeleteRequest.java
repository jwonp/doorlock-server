package com.ikiningyou.drserver.model.dto.user.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDeleteRequest {
    private String[] idList;
}
