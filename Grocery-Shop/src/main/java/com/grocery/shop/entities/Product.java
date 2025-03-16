package com.grocery.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "productId")
    private int id;

    @Column(name = "productTitle", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    private int price;

    @Column(name = "discountedPrice")
    private int discountedPrice;

    private boolean live;
}
