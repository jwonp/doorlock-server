package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, String> {}
