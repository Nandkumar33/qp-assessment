package com.grocery.shop.controllers;

import com.grocery.shop.dtos.ApiResponseMessage;
import com.grocery.shop.dtos.OrderDto;
import com.grocery.shop.dtos.PageableResponse;
import com.grocery.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto, @RequestParam int userId, @RequestParam int cartId) {
        OrderDto order = orderService.createOrder(orderDto, userId, cartId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseMessage> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder().message("Order deleted Successfully!!!").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PageableResponse<OrderDto>> getAllOrders(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "orderedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ) {
        PageableResponse<OrderDto> allOrders = orderService.getAllOrders(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int orderId) {
        OrderDto order = orderService.updateOrder(orderDto, orderId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
