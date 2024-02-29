package com.nhlong.apiserver.services.impl;

import com.nhlong.apiserver.dtos.requestDtos.CustomerRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.CustomerResponseDto;
import com.nhlong.apiserver.exceptions.FetchException;
import com.nhlong.apiserver.mappers.CustomerMapper;
import com.nhlong.apiserver.models.Customer;

import com.nhlong.apiserver.repositories.CustomerRepository;
import com.nhlong.apiserver.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;



@Service
@Validated
public class CustomerServiceImpl implements CustomerService {
    public final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {

        return customerRepository.findAllByStatus(true)
                .stream()
                .map(CustomerMapper::mapCustomerToResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto createCustomer(@Valid CustomerRequestDto customerRequestDto) {
        Customer newCustomer = CustomerMapper.mapRequestToCustomer(customerRequestDto);
        newCustomer.setStatus(true);
        Customer createdCustomer = customerRepository
                .save(newCustomer);
        return CustomerMapper.mapCustomerToResponseDto(createdCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findCustomerByIdAndStatus(id, true)
                .orElseThrow(() -> new FetchException("Customer with" + id + " not found !!"));
        return CustomerMapper.mapCustomerToResponseDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(@Valid CustomerRequestDto customerRequestDto, Long id) {

        if (!customerRepository.existsById(id)) {
            throw new FetchException("Customer with" + id + "not found");
        }
        Customer customer = CustomerMapper.mapRequestToCustomer(customerRequestDto);
        customer.setId(id);
        customerRepository.save(customer);
        return CustomerMapper.mapCustomerToResponseDto(customer);
    }


    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new FetchException("Customer with" + id + " not found !!"));
        customer.setStatus(false);
        customerRepository.save(customer);
    }

    public boolean existsByName(String name) {
        return customerRepository.existsByNameEN(name);
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }


}
