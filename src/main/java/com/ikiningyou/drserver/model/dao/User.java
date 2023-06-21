package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
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

  @Column(name = "last_tagged", nullable = true)
  private Date lastTagged;

  @Column(name = "room_id", nullable = true)
  private String roomId;

  @Column(name = "card_id", nullable = true)
  private String cardId;
}
