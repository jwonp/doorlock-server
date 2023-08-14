package com.ikiningyou.drserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikiningyou.drserver.model.dao.TagLog;

public interface LogRepository extends JpaRepository<TagLog,Long>{
    
}
