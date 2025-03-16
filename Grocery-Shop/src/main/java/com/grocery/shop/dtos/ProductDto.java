package com.grocery.shop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private int id;

    @NotBlank(message = "Please Enter title of product")
    @Size(min = 5, max = 40, message = "Please enter min 5 and max 40 length of title")
    private String title;

    @Size(max = 1000, message = "Please enter max 10000 size of title")
    private String description;

    @PositiveOrZero(message = "Please enter price in positive value")
    private int price;

    @PositiveOrZero(message = "Please enter price in positive value")
    private int discountedPrice;

    private boolean live;

}
