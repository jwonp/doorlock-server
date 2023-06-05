package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

  @Id
  @Column(name = "room_id", nullable = false)
  private int id;

  @Column(name = "public_key", nullable = false)
  private String publicKey;

  @Column(name = "guest_id", nullable = true)
  private String guestId;
}
