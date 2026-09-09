package com.jmfashions.webapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jmfashions.webapp.entity.LoginEntity;
import com.jmfashions.webapp.entity.OrderEntity;
import com.jmfashions.webapp.entity.OrderItemEntity;
import com.jmfashions.webapp.repository.OrderRepository;
import com.jmfashions.webapp.repository.OrderItemRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Save Order
    public OrderEntity placeOrder(OrderEntity order,
                                  List<OrderItemEntity> items) {

        // Set order date
        order.setOrderDate(LocalDateTime.now());

        // Save order first
        OrderEntity savedOrder =
                orderRepository.save(order);

        // Save each order item
        for (OrderItemEntity item : items) {

            item.setOrder(savedOrder);

            // Calculate subtotal
            double subtotal =
                    item.getPrice() * item.getQuantity();

            item.setSubtotal(subtotal);

            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    // Get orders by user
    public List<OrderEntity> getOrdersByUser(LoginEntity user) {

        return orderRepository.findByUser(user);
    }
}
