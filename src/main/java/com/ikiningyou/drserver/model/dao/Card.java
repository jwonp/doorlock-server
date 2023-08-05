package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {

  @Id
  @Column(name = "card_id", nullable = false)
  private String id;

  @Column(name = "max_size", nullable = false)
  private int maxSize;

  @Column(name = "type", nullable = false)
  private String type;

  // tech types flag

  @Column(name = "is_iso_dep", nullable = false)
  private boolean isIsoDep;

  @Column(name = "is_nfc_a", nullable = false)
  private boolean isNfcA;

  @Column(name = "is_nfc_b", nullable = false)
  private boolean isNfcB;

  @Column(name = "is_nfc_f", nullable = false)
  private boolean isNfcF;

  @Column(name = "is_nfc_v", nullable = false)
  private boolean isNfcV;

  @Column(name = "is_ndef", nullable = false)
  private boolean isNdef;

  @Column(name = "is_ndef_formatable", nullable = false)
  private boolean isNdefFormatable;

  @Column(name = "is_mifare_classic", nullable = false)
  private boolean isMifareClassic;

  @Column(name = "is_mifate_ultralight", nullable = false)
  private boolean isMifareUltralight;

  @Column(name = "is_admin", nullable = false)
  private boolean isAdmin;

  @Column(name = "last_tagged", nullable = true)
  private Date lastTagged;

  @OneToOne(mappedBy = "card")
  private Reservation reservation;
}
