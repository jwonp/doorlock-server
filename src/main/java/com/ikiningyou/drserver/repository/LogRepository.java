package com.ikiningyou.drserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikiningyou.drserver.model.dao.TagLog;

public interface LogRepository extends JpaRepository<TagLog,Long>{

    List<TagLog> findByResultContaining(String result);
    
}
