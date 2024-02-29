package com.nhlong.apiserver.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String productName;
    private String description;
    private double price;

    private TranslationProductResponseDto productNameTranslation;
    private TranslationProductResponseDto productDescTranslation;

    public ProductResponseDto() {

    }
}
