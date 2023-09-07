package com.ikiningyou.drserver.model.dto.auth.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthLoginRequest {
    private String username;
    private String password;
}
