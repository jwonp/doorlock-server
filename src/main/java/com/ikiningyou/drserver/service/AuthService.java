package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private LogRepository logRepository;

  public void addTagLog(String cardId, String result, String address) {
    TagLog log = TagLog
      .builder()
      .cardId(cardId)
      .address(address)
      .result(result)
      .build();
    try {
      logRepository.save(log);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
  }
}
