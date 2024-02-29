package com.nhlong.apiserver.controllers;

import com.nhlong.apiserver.dtos.requestDtos.ProductRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.ProductResponseDto;
import com.nhlong.apiserver.exceptions.FetchException;
import com.nhlong.apiserver.mappers.ProductMapper;
import com.nhlong.apiserver.models.Product;
import com.nhlong.apiserver.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequestDto) {
        if (productService.existsByName(productRequestDto.getProductNameEN())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This product already exists");
        }
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return ResponseEntity.status(201).body(productResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable Long id) {
        try {
            ProductResponseDto productResponseDto = productService.getProductById(id);
            return ResponseEntity.ok(productResponseDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, ProductRequestDto productRequestDto) {
        if (!productService.existsById(id)) {
            throw new FetchException("This product with id = " + id + "not exist");
        }
        Product product = ProductMapper.mapRequestToProduct(productRequestDto);
        product.setId(id);
        ProductResponseDto updatedProduct = productService.updateProduct(productRequestDto, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted id" + id);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> productResponseDtos = productService.getAllProduct();
        if (productResponseDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(productResponseDtos);

    }
}
