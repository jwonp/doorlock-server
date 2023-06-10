package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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

  @Column(name = "guest_id", nullable = true)
  private String guestId;

  @Column(name = "is_used", nullable = false)
  private boolean isUsed;

  @PrePersist
  void preSetIsUsed() {
    this.isUsed = false;
  }
}
