package com.nhlong.apiserver.services;

import com.nhlong.apiserver.dtos.requestDtos.CustomerRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.CustomerResponseDto;
import com.nhlong.apiserver.models.Customer;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface CustomerService {
    List<CustomerResponseDto> getAllCustomer();

    CustomerResponseDto createCustomer(@Valid CustomerRequestDto customerRequestDto);
    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto updateCustomer(@Valid CustomerRequestDto customerRequestDto, Long id);

    void deleteCustomer(Long id);

    boolean existsByName(String name);
    boolean existsById(Long id);
}
