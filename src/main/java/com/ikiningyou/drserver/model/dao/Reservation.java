package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reservation_id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Column(name = "user_id")
  private String userId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", insertable = false, updatable = false)
  private Card card;

  @Column(name = "card_id")
  private String cardId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", insertable = false, updatable = false)
  private Room room;

  @Column(name = "room_id")
  private int roomId;

  // @Column(name = "client_id", nullable = false)
  // private String clientId;

  // @Column(name = "card_id", nullable = false)
  // private String cardId;

  // @Column(name = "room_id", nullable = false)
  // private int roomId;

  @Column(name = "is_checked_in", nullable = false)
  private boolean isCheckedIn;

  @PrePersist
  void preSetIsCheckedIn() {
    this.isCheckedIn = false;
  }
}
