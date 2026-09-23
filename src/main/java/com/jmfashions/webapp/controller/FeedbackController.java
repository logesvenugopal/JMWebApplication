
package com.jmfashions.webapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfashions.webapp.entity.FeedbackEntity;
import com.jmfashions.webapp.repository.FeedbackRepository;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    // Save new feedback
    @PostMapping
    public ResponseEntity<String> saveFeedback(
            @RequestBody FeedbackEntity feedback) {

        feedbackRepository.save(feedback);

        return ResponseEntity.ok("Feedback submitted successfully");
    }

    // Get latest 3 feedbacks
    @GetMapping("/recent")
    public ResponseEntity<List<FeedbackEntity>> getRecentFeedbacks() {

        List<FeedbackEntity> feedbacks =
                feedbackRepository.findTop3ByOrderByIdDesc();

        return ResponseEntity.ok(feedbacks);
    }
}
