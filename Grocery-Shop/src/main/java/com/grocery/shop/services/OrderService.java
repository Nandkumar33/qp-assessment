package com.grocery.shop.services;

import com.grocery.shop.dtos.OrderDto;
import com.grocery.shop.dtos.PageableResponse;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto, int userId, int cartId);

    void deleteOrder(int orderId);

    PageableResponse<OrderDto> getAllOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    OrderDto updateOrder(OrderDto orderDto, int orderId);
}
