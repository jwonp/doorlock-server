package com.ikiningyou.drserver.model.dto.card;

import com.ikiningyou.drserver.model.data.TechType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardResponse {

  private String id;
  private int maxSize;
  private String type;
  private TechType techType;
}
