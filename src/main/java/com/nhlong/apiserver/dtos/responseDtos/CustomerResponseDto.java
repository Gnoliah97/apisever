package com.nhlong.apiserver.dtos.responseDtos;

import lombok.Data;

import java.util.Date;
@Data
public class CustomerResponseDto {
    private Long id;
    private String name_EN;
    private Date dateOfBirth;
    private String addressEN;
    private String contactNumber;
    private TranslationCustomerResponseDto nameTranslation;
    private TranslationCustomerResponseDto addressTranslation;
}
