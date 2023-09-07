package com.ikiningyou.drserver.model.dto.card.mobile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CardAddRequest {

  private String id;
  private int maxSize;
  private String[] techTypes;
  private String type;
}
