package com.ikiningyou.drserver.util;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.TechType;
import org.springframework.stereotype.Component;

@Component
public class TechTypeBuilder {

   public TechType build(Card card) {
    TechType techType = TechType
      .builder()
      .isIsoDep(card.isIsoDep())
      .isNfcA(card.isNfcA())
      .isNfcB(card.isNfcB())
      .isNfcF(card.isNfcF())
      .isNfcV(card.isNfcV())
      .isNdef(card.isNdef())
      .isNdefFormatable(card.isNdefFormatable())
      .isMifareClassic(card.isMifareClassic())
      .isMifareUltralight(card.isMifareUltralight())
      .build();
    return techType;
  }
}
