package com.jmfashions.webapp.repository;

import com.jmfashions.webapp.entity.OrderEntity;
import com.jmfashions.webapp.entity.LoginEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUser(LoginEntity user);
}
