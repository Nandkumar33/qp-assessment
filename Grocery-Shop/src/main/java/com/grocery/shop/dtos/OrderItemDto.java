package com.grocery.shop.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private int orderItemId;
    private int quantity;
    private int totalAmount;
    private ProductDto product;
}
