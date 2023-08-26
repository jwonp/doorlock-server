package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

  @Id
  @Column(name = "user_id", nullable = false)
  private String id;

  @Column(name = "user_name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

  @OneToMany(mappedBy = "user")
  private List<Reservation> reservations;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", updatable = true)
  private UserDetail userDetail;
}
