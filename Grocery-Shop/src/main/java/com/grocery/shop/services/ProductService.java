package com.grocery.shop.services;

import com.grocery.shop.dtos.PageableResponse;
import com.grocery.shop.dtos.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(int productId, ProductDto productDto);

    void deleteProduct(int productId);

    ProductDto getProductById(int productId);

    PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);
}
