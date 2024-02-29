package com.nhlong.apiserver.controllers;

import com.nhlong.apiserver.dtos.requestDtos.CustomerRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.CustomerResponseDto;
import com.nhlong.apiserver.exceptions.CRUDException;
import com.nhlong.apiserver.exceptions.FetchException;
import com.nhlong.apiserver.mappers.CustomerMapper;
import com.nhlong.apiserver.models.Customer;
import com.nhlong.apiserver.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/customers")
@Validated

public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        if (customerService.existsByName(customerRequestDto.getNameEN())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate Customer");
        }
        CustomerResponseDto customerResponseDto = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(201).body(customerResponseDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> get(@PathVariable Long id) {
        try {
            CustomerResponseDto getCustomerResponseDto = customerService.getCustomerById(id);
            return ResponseEntity.ok(getCustomerResponseDto);
        } catch (FetchException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@Valid @PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        if (!customerService.existsById(id)) {
            throw new CRUDException("Not found customer with id =" + id);
        }
        Customer customer = CustomerMapper.mapRequestToCustomer(customerRequestDto);
        customer.setId(id);

        CustomerResponseDto updatedCustomer = customerService.updateCustomer(customerRequestDto, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
        } catch (FetchException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Deleted customer with id: " + id);

    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAll() {
        List<CustomerResponseDto> customerResponseDtos = customerService.getAllCustomer();
        if (customerResponseDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(customerResponseDtos);
    }
}
