package com.nhlong.apiserver.dtos.requestDtos;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerRequestDto {
    private String nameEN;
    private Date dateOfBirth;
    private String addressEN;
    private String contactNumber;
}
