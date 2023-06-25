package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_id", nullable = false)
  private int id;

  @Column(name = "address", nullable = false)
  private String address;

  @OneToOne(mappedBy = "room")
  private Reservation reservation;
}
