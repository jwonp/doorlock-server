package com.ikiningyou.drserver.model.data;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TechType {

  private boolean isIsoDep;
  private boolean isNfcA;
  private boolean isNfcB;
  private boolean isNfcF;
  private boolean isNfcV;
  private boolean isNdef;
  private boolean isNdefFormatable;
  private boolean isMifareClassic;
  private boolean isMifareUltralight;
}
