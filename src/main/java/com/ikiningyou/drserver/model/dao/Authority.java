package com.ikiningyou.drserver.model.dao;

import com.ikiningyou.drserver.model.dao.idClass.AuthorityId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "authority")
public class Authority {

  @Id
  @Column(name = "user")
  private String user;

  @Column(name = "authority", nullable = false)
  private String authority;
}
