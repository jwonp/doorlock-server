package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "lostcard")
public class LostCard {

  @Id
  @Column(name = "card_id", nullable = false)
  private String id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @MapsId
  @JoinColumn(name = "card_id")
  private Card card;

  @CreationTimestamp
  @Column(name = "requested_time")
  private Timestamp requestedTime;
}
