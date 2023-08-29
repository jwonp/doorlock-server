package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminResponse;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminUnauthorizedResponse;
import com.ikiningyou.drserver.repository.LogRepository;
import com.ikiningyou.drserver.util.CardResults;
import com.ikiningyou.drserver.util.builder.log.LogBuilder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  @Autowired
  private LogRepository logRepository;

  public TagLog[] getAllLogs() {
    List<TagLog> logList = logRepository.findAll();
    TagLog[] logArray = logList.toArray(new TagLog[logList.size()]);

    return logArray;
  }

  public LogAdminUnauthorizedResponse[] getUnauthorizedTagLogs() {
    List<TagLog> logs = logRepository.findByResultContaining(
      CardResults.CARD_UNAUTHORIZED
    );
    return logs
      .stream()
      .map(item -> LogBuilder.TagLogToLogAdminUnauthorizedResponse(item))
      .toArray(LogAdminUnauthorizedResponse[]::new);
  }

  public LogAdminResponse[] getAdminTagLogs() {
    List<TagLog> logs = logRepository.findAll();
    return logs
      .stream()
      .map(item -> LogBuilder.TagLogToLogAdminResponse(item))
      .toArray(LogAdminResponse[]::new);
  }
}
