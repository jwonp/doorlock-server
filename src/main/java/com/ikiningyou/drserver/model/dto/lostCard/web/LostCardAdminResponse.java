package com.ikiningyou.drserver.model.dto.lostCard.web;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LostCardAdminResponse {
    private String cardId;
    private String userId;
    private String name;
    private String phone;
    private String address;
    private LocalDateTime lastTagged;
}
