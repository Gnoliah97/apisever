package com.nhlong.apiserver.services;

import com.nhlong.apiserver.dtos.requestDtos.ProductRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProduct();
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(Long id);

    ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id);

    void deleteProduct(Long id);

    boolean existsByName(String name);
    boolean existsById(Long id);
}
