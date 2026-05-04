package com.freelancing.platform.repository;

import com.freelancing.platform.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByRevieweeId(String revieweeId);
}
