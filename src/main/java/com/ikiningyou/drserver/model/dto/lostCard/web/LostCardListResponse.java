package com.ikiningyou.drserver.model.dto.lostCard.web;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LostCardListResponse {
    private String cardId;
    private String name;
    private String userId;
    private String phone;
    private String address;
    private LocalDateTime lastTagged;

}
