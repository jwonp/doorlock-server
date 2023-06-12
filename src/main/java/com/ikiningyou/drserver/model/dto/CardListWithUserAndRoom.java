package com.ikiningyou.drserver.model.dto;

public interface CardListWithUserAndRoom {
  public String getId();

  public int getMaxSize();

  public String getType();

  // tech types flag

  public boolean getIsIsoDep();

  public boolean getIsNfcA();

  public boolean getIsNfcB();

  public boolean getIsNfcF();

  public boolean getIsNfcV();

  public boolean getIsNdef();

  public boolean getIsNdefFormatable();

  public boolean getIsMifareClassic();

  public boolean getIsMifareUltralight();

  public boolean getIsUsed();

  public String getUserId();

  public int getRoomId();
}
