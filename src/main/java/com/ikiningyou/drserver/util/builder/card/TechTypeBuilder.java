package com.ikiningyou.drserver.util.builder.card;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.TechType;
import org.springframework.stereotype.Component;

@Component
public class TechTypeBuilder {

  public static TechType CardtoTechType(Card card) {
    return TechType
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
  }
}
