package com.ikiningyou.drserver.model.dto.log.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LogAdminUnauthorizedResponse {
    private Long logId;
    private String cardId;
    private String address;
    private Timestamp taggedTime;
}
