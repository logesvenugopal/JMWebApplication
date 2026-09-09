package com.jmfashions.webapp.repository;

import com.jmfashions.webapp.entity.OrderItemEntity;
import com.jmfashions.webapp.entity.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findByOrder(OrderEntity order);
}