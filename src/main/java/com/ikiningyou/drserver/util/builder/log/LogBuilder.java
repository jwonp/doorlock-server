package com.ikiningyou.drserver.util.builder.log;

import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminResponse;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminUnauthorizedResponse;

public class LogBuilder {

  public static LogAdminUnauthorizedResponse TagLogToLogAdminUnauthorizedResponse(
    TagLog log
  ) {
    return LogAdminUnauthorizedResponse
      .builder()
      .logId(log.getId())
      .cardId(log.getCardId())
      .address(log.getAddress())
      .taggedTime(log.getTaggedTime())
      .build();
  }

  public static LogAdminResponse TagLogToLogAdminResponse(TagLog log) {
    return LogAdminResponse
      .builder()
      .logId(log.getId())
      .result(log.getResult())
      .cardId(log.getCardId())
      .address(log.getAddress())
      .taggedTime(log.getTaggedTime())
      .build();
  }
}
