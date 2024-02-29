package com.nhlong.apiserver.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationProductResponseDto {
    private Long id;
    private String text;
}
