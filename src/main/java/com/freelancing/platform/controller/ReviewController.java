package com.freelancing.platform.controller;

import com.freelancing.platform.dto.request.ReviewRequest;
import com.freelancing.platform.dto.response.ReviewDto;
import com.freelancing.platform.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewRequest request, Authentication authentication) {
        ReviewDto createdReview = reviewService.createReview(request, authentication.getName());
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(reviewService.getReviewsForUser(userId));
    }
}
