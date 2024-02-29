package com.nhlong.apiserver.dtos.requestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductRequestDto {
    private String productNameEN;
    private String descEN;
    private double price;

}
