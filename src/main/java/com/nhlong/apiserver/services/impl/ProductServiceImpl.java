package com.nhlong.apiserver.services.impl;

import com.nhlong.apiserver.dtos.requestDtos.ProductRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.ProductResponseDto;
import com.nhlong.apiserver.exceptions.FetchException;
import com.nhlong.apiserver.mappers.ProductMapper;
import com.nhlong.apiserver.models.Product;
import com.nhlong.apiserver.repositories.ProductRepository;
import com.nhlong.apiserver.services.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product newProduct = ProductMapper.mapRequestToProduct(productRequestDto);
        newProduct.setStatus(true);
        Product createProduct = productRepository.save(newProduct);
        return ProductMapper.mapProductToResponse(createProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findProductByIdAndStatus(id, true)
                .orElseThrow(() -> new FetchException("Product with " + id + " not found"));

        return ProductMapper.mapProductToResponse(product);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id) {
        if (!productRepository.existsById(id)){
            throw new FetchException("Product with " + id + " not found");
        }
        Product product = ProductMapper.mapRequestToProduct(productRequestDto);
        product.setId(id);
        productRepository.save(product);
        return ProductMapper.mapProductToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        return productRepository.findAllByStatus(true)
                .stream()
                .map(ProductMapper::mapProductToResponse).toList();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new FetchException("Product with id =" + id + " not found"));
        product.setStatus(false);
        productRepository.save(product);

    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByProductName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
