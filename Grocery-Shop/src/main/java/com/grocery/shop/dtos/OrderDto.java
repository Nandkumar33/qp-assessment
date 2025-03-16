package com.grocery.shop.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private int orderId;
    private String orderStatus = "PENDING";
    private boolean isPrepaid = false;
    private int orderAmount;
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date orderedDate = new Date();
    private Date deliveredDate;
    private UserDto user;
    private List<OrderItemDto> orderItems = new ArrayList<>();
}
