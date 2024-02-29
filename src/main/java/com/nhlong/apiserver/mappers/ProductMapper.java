package com.nhlong.apiserver.mappers;

import com.nhlong.apiserver.dtos.requestDtos.ProductRequestDto;
import com.nhlong.apiserver.dtos.responseDtos.ProductResponseDto;
import com.nhlong.apiserver.dtos.responseDtos.TranslationProductResponseDto;
import com.nhlong.apiserver.models.Product;
import com.nhlong.apiserver.models.TranslationProduct;

public class ProductMapper {
    public static Product mapRequestToProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setProductName(productRequestDto.getProductNameEN());
        product.setDescriptionEN(productRequestDto.getDescEN());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }

    public static ProductResponseDto mapProductToResponse(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        TranslationProduct productNameTrans = product.getProductNameTranslation();
        TranslationProduct descTrans = product.getProductDescTranslation();
        if (productNameTrans != null){
            TranslationProductResponseDto productNameTransDto = new TranslationProductResponseDto(productNameTrans.getId(), productNameTrans.getText());
            productResponseDto.setProductNameTranslation(productNameTransDto);
        }
        if (descTrans != null){
            TranslationProductResponseDto productDescTransDto = new TranslationProductResponseDto(descTrans.getId(), descTrans.getText());
            productResponseDto.setProductNameTranslation(productDescTransDto);
        }
        productResponseDto.setId(product.getId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setDescription(product.getDescriptionEN());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;

    }
}
