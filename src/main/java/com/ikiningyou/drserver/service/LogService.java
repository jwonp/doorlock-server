package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.repository.LogRepository;
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
}
