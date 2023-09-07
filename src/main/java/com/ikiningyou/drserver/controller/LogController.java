package com.ikiningyou.drserver.controller;

import com.ikiningyou.drserver.model.dao.TagLog;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminResponse;
import com.ikiningyou.drserver.model.dto.log.web.LogAdminUnauthorizedResponse;

import com.ikiningyou.drserver.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

  @Autowired
  private LogService logService;

  @GetMapping("/list")
  public ResponseEntity<TagLog[]> getAllLogs() {
    TagLog[] logs = logService.getAllLogs();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(logs);
  }

  @GetMapping("/admin/unauthorized")
  public ResponseEntity<LogAdminUnauthorizedResponse[]> getUnauthorizedTagLogs() {
    LogAdminUnauthorizedResponse[] logs = logService.getUnauthorizedTagLogs();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(logs);
  }

  @GetMapping("/admin")
  public ResponseEntity<LogAdminResponse[]> getAdminTagLogs() {
    LogAdminResponse[] logs = logService.getAdminTagLogs();
    HttpStatus status = HttpStatus.OK;
    return ResponseEntity.status(status).body(logs);
  }
}
