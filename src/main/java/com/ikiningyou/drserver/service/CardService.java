package com.ikiningyou.drserver.service;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.dto.CardAddRequest;
import com.ikiningyou.drserver.repository.CardRepository;
import com.ikiningyou.drserver.util.NfcCardTechTypeParser;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private NfcCardTechTypeParser nfcCardTechTypeParser;

  public Card[] getAllCards() {
    List<Card> cardList = cardRepository.findAll();
    return cardList.toArray(new Card[cardList.size()]);
  }

  public String addCard(CardAddRequest newCard) {
    // 만약 등록할 카드가 DB에 있었다면 바로 리턴
    Optional<Card> existedCard = cardRepository.findById(newCard.getId());
    if (existedCard.isPresent() == true) {
      return "";
    }

    String[] techTypes = newCard.getTechTypes();
    boolean[] techTypeFlags = nfcCardTechTypeParser.parseTechType(techTypes);
    Card card = Card
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

    try {
      Card savedCard= cardRepository.save(card);
      return savedCard.getId();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace();
    }
    return "";
  }
}
