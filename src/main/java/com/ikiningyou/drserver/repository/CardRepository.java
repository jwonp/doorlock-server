package com.ikiningyou.drserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikiningyou.drserver.model.dao.Card;

public interface CardRepository extends JpaRepository<Card,String> {
    
}
