package com.ikiningyou.drserver.util.builder.card;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.TechType;
import com.ikiningyou.drserver.util.NfcCardTechTypeParser;
import org.springframework.stereotype.Component;

@Component
public class TechTypeBuilder {

  public static TechType StringArrayToTechType(String[] techTypes) {
    boolean[] parsedTechTypeFlags = NfcCardTechTypeParser.parseTechType(
      techTypes
    );
    return TechType
      .builder()
      .isIsoDep(parsedTechTypeFlags[NfcCardTechTypeParser.IS_ISO_DEP])
      .isNfcA(parsedTechTypeFlags[NfcCardTechTypeParser.IS_NFC_A])
      .isNfcB(parsedTechTypeFlags[NfcCardTechTypeParser.IS_NFC_B])
      .isNfcF(parsedTechTypeFlags[NfcCardTechTypeParser.IS_NFC_F])
      .isNfcV(parsedTechTypeFlags[NfcCardTechTypeParser.IS_NFC_V])
      .isNdef(parsedTechTypeFlags[NfcCardTechTypeParser.IS_NDEF])
      .isNdefFormatable(
        parsedTechTypeFlags[NfcCardTechTypeParser.IS_NDEF_FORMATABLE]
      )
      .isMifareClassic(
        parsedTechTypeFlags[NfcCardTechTypeParser.IS_MIFARE_CLASSIC]
      )
      .isMifareUltralight(
        parsedTechTypeFlags[NfcCardTechTypeParser.IS_MIFARE_ULTRA_LIGHT]
      )
      .build();
  }

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
