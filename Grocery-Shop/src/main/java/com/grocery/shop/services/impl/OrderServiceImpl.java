package com.grocery.shop.services.impl;

import com.grocery.shop.dtos.OrderDto;
import com.grocery.shop.dtos.PageableResponse;
import com.grocery.shop.entities.Order;
import com.grocery.shop.entities.OrderItem;
import com.grocery.shop.entities.User;
import com.grocery.shop.exceptions.ResourceNotFoundException;
import com.grocery.shop.helpers.Helper;
import com.grocery.shop.repositories.OrderRepository;
import com.grocery.shop.repositories.UserRepository;
import com.grocery.shop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto, int userId, int cartId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for given user id"));
        Order order = mapper.map(orderDto, Order.class);
        SecureRandom random = new SecureRandom();
        int orderId = random.nextInt(1000);
        order.setOrderId(orderId);
        order.setDeliveredDate(null);
        order.setUser(user);
        List<OrderItem> orderItems = order.getOrderItems();
        AtomicInteger orderAmount = new AtomicInteger();
        orderItems = orderItems.stream().map(orderItem -> {
            OrderItem newItem = OrderItem.builder().order(order).product(orderItem.getProduct()).quantity(orderItem.getQuantity()).totalAmount(orderItem.getTotalAmount()).orderItemId(orderItem.getOrderItemId()).build();
            orderAmount.set(orderAmount.get() + newItem.getTotalAmount());
            return newItem;
        }).toList();
        order.setOrderAmount(orderAmount.get());
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for given id"));
        orderRepository.delete(order);
    }

    @Override
    public PageableResponse<OrderDto> getAllOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Order> page = orderRepository.findAll(pageable);
        return Helper.getPageableResponse(page, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for given order id."));
        order.setOrderStatus(orderDto.getOrderStatus());
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }
}
