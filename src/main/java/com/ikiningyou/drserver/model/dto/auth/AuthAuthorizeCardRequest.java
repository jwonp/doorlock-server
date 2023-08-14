package com.ikiningyou.drserver.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthAuthorizeCardRequest {
    private String cardId;
    private String address;
}
