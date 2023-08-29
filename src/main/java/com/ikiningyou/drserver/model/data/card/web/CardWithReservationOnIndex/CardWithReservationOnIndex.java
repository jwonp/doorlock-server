package com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex;

import java.time.LocalDateTime;

public interface CardWithReservationOnIndex {
  public String getCardId();

  public LocalDateTime getLastTagged();

  public Long getreservationId();

  public String getName();

  public String getUserId();

  public String getPhone();

  public String getAddress();
}
