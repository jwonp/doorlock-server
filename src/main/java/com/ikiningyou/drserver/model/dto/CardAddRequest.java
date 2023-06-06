package com.ikiningyou.drserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardAddRequest {
    private String id;
    private int maxSize;
    private String[] techTypes;
    private String type;
}
