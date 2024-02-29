package com.nhlong.apiserver.dtos.responseDtos;

import com.nhlong.apiserver.models.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationCustomerResponseDto {


    private Long id;


    private String text;
}
