package com.ikiningyou.drserver.util;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NfcCardTechTypeParser {

  public static final int IS_ISO_DEP = 0;
  public static final int IS_NFC_A = 1;
  public static final int IS_NFC_B = 2;
  public static final int IS_NFC_F = 3;
  public static final int IS_NFC_V = 4;
  public static final int IS_NDEF = 5;
  public static final int IS_NDEF_FORMATABLE = 6;
  public static final int IS_MIFARE_CLASSIC = 7;
  public static final int IS_MIFARE_ULTRA_LIGHT = 8;

  private final String ISO_DEP = "IsoDep";
  private final String NFC_A = "NfcA";
  private final String NFC_B = "NfcB";
  private final String NFC_F = "NfcF";
  private final String NFC_V = "NfcV";
  private final String NDEF = "Ndef";
  private final String NDEF_FORMATABLE = "NdefFormatable";
  private final String MIFARE_CLASSIC = "MifareClassic";
  private final String MIFARE_ULTRA_LIGHT = "MifareUltralight";

  private final String[] TECH_TYPES = {
    ISO_DEP,
    NFC_A,
    NFC_B,
    NFC_F,
    NFC_V,
    NDEF,
    NDEF_FORMATABLE,
    MIFARE_CLASSIC,
    MIFARE_ULTRA_LIGHT,
  };

  private final List<String> TECH_TYPE_LIST = Arrays.asList(TECH_TYPES);

  public boolean[] parseTechType(String[] techTypes) {
    boolean[] techTypeFlags = new boolean[9];

    for (String techType : techTypes) {
      int techTypeIndex = TECH_TYPE_LIST.indexOf(techType);
      if (techTypeIndex < 0) {
        continue;
      }
      techTypeFlags[techTypeIndex] = true;
    }

    return techTypeFlags;
  }
}
