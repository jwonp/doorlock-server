package com.ikiningyou.drserver.model.dto.card.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardDeleteRequest {

  String[] idList;
}
