package com.ikiningyou.drserver.repository;

import com.ikiningyou.drserver.model.dao.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentifierRepository
  extends JpaRepository<Identifier, String> {}
