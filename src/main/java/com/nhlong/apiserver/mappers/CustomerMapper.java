package com.nhlong.apiserver.mappers;

import com.nhlong.apiserver.dtos.requestDtos.CustomerRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.CustomerResponseDto;
import com.nhlong.apiserver.dtos.responseDtos.TranslationCustomerResponseDto;
import com.nhlong.apiserver.models.Customer;
import com.nhlong.apiserver.models.TranslationCustomer;

public class CustomerMapper {
    public static Customer mapRequestToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = new Customer();

        customer.setNameEN(customerRequestDto.getNameEN());
        customer.setAddressEN(customerRequestDto.getAddressEN());
        customer.setContactNumber(customerRequestDto.getContactNumber());
        customer.setDateOfBirth(customerRequestDto.getDateOfBirth());
        return customer;
    }

    public static CustomerResponseDto mapCustomerToResponseDto(Customer customer){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        TranslationCustomer nameTrans = customer.getNameTranslation();
        TranslationCustomer addressTrans = customer.getAddressTranslation();
        if(nameTrans != null){
            TranslationCustomerResponseDto nameTransDto = new TranslationCustomerResponseDto(nameTrans.getId(), nameTrans.getText());
            customerResponseDto.setNameTranslation(nameTransDto);
        }if(addressTrans != null){
            TranslationCustomerResponseDto addressTransDto = new TranslationCustomerResponseDto(addressTrans.getId(), addressTrans.getText());
            customerResponseDto.setAddressTranslation(addressTransDto);
        }
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setName_EN(customer.getNameEN());
        customerResponseDto.setContactNumber(customer.getContactNumber());
        customerResponseDto.setAddressEN(customer.getAddressEN());
        customerResponseDto.setDateOfBirth(customer.getDateOfBirth());
        return customerResponseDto;
    }
}
