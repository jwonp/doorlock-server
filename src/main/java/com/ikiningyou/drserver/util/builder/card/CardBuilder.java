package com.ikiningyou.drserver.util.builder.card;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.card.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.CardResponse;
import com.ikiningyou.drserver.util.NfcCardTechTypeParser;

public class CardBuilder {

  public static CardResponse CardToCardResponse(Card card) {
    return CardResponse
      .builder()
      .id(card.getId())
      .maxSize(card.getMaxSize())
      .type(card.getType())
      .techType(TechTypeBuilder.CardtoTechType(card))
      .build();
  }

  public static Card CardAddRequestToCard(CardAddRequest newCard) {
    String[] techTypes = newCard.getTechTypes();
    boolean[] techTypeFlags = NfcCardTechTypeParser.parseTechType(techTypes);
    return Card
      .builder()
      .id(newCard.getId())
      .maxSize(newCard.getMaxSize())
      .type(newCard.getType())
      .isIsoDep(techTypeFlags[NfcCardTechTypeParser.IS_ISO_DEP])
      .isNfcA(techTypeFlags[NfcCardTechTypeParser.IS_NFC_A])
      .isNfcB(techTypeFlags[NfcCardTechTypeParser.IS_NFC_B])
      .isNfcF(techTypeFlags[NfcCardTechTypeParser.IS_NFC_F])
      .isNfcV(techTypeFlags[NfcCardTechTypeParser.IS_NFC_V])
      .isNdef(techTypeFlags[NfcCardTechTypeParser.IS_NDEF])
      .isNdefFormatable(techTypeFlags[NfcCardTechTypeParser.IS_NDEF_FORMATABLE])
      .isMifareClassic(techTypeFlags[NfcCardTechTypeParser.IS_MIFARE_CLASSIC])
      .isMifareUltralight(
        techTypeFlags[NfcCardTechTypeParser.IS_MIFARE_ULTRA_LIGHT]
      )
      .build();
  }
}
