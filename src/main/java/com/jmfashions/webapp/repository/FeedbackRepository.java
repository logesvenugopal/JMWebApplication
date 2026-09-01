package com.jmfashions.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmfashions.webapp.entity.FeedbackEntity;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

}
