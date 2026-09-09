package com.jmfashions.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmfashions.webapp.DTO.OrderItemRequest;
import com.jmfashions.webapp.DTO.OrderRequest;
import com.jmfashions.webapp.entity.LoginEntity;
import com.jmfashions.webapp.entity.OrderEntity;    
import com.jmfashions.webapp.entity.OrderItemEntity;
import com.jmfashions.webapp.repository.OrderRepository;
import com.jmfashions.webapp.service.OrderService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService,
                           OrderRepository orderRepository) {

        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    // ============================
    // PLACE ORDER
    // ============================

   @PostMapping
public ResponseEntity<?> placeOrder(
        @RequestBody OrderRequest request,
        HttpSession session) {

    System.out.println("========== ORDER API CALLED ==========");

    try {

        // Get logged-in user from session
        Long userId = (Long) session.getAttribute("userId");

        // User not logged in
        if (userId == null) {

            return ResponseEntity
                    .status(401)
                    .body("Please login to place your order.");
        }

        // Create user reference
        LoginEntity user = new LoginEntity();
        user.setId(userId);

        // Create Order
        OrderEntity order = new OrderEntity();

        order.setUser(user);
        order.setName(request.getName());
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setPincode(request.getPincode());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setPaymentDetails(request.getPaymentDetails());
        order.setTotalAmount(request.getTotalAmount());

        // Create Order Items
        List<OrderItemEntity> items = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {

            OrderItemEntity item = new OrderItemEntity();

            item.setProductName(itemRequest.getProductName());
            item.setImage(itemRequest.getImage());
            item.setSize(itemRequest.getSize());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());

            items.add(item);
        }

        // Save order + items
        OrderEntity savedOrder =
                orderService.placeOrder(order, items);

        return ResponseEntity.ok(savedOrder);

    } catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity
                .badRequest()
                .body("Order failed: " + e.getMessage());
    }
}

    // ============================
    // GET USER ORDERS
    // ============================

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserOrders(
            @PathVariable Long userId) {

        try {

            LoginEntity user = new LoginEntity();
            user.setId(userId);

            return ResponseEntity.ok(
                    orderRepository.findByUser(user)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Could not fetch orders: " + e.getMessage());
        }
    }
}
