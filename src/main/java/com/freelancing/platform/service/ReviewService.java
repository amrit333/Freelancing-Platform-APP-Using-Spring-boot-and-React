package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.ReviewRequest;
import com.freelancing.platform.dto.response.ReviewDto;
import com.freelancing.platform.entity.Project;
import com.freelancing.platform.entity.Review;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.entity.enums.ProjectStatus;
import com.freelancing.platform.exception.BadRequestException;
import com.freelancing.platform.exception.ResourceNotFoundException;
import com.freelancing.platform.mapper.ReviewMapper;
import com.freelancing.platform.repository.ProjectRepository;
import com.freelancing.platform.repository.ReviewRepository;
import com.freelancing.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto createReview(ReviewRequest request, String reviewerEmail) {
        User reviewer = userRepository.findByEmail(reviewerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Reviewer not found"));

        User reviewee = userRepository.findById(request.getRevieweeId())
                .orElseThrow(() -> new ResourceNotFoundException("Reviewee not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (project.getStatus() != ProjectStatus.COMPLETED) {
            throw new BadRequestException("Can only review completed projects");
        }

        Review review = reviewMapper.toEntity(request);
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setProject(project);
        review.prePersist();

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    public List<ReviewDto> getReviewsForUser(String userId) {
        List<Review> reviews = reviewRepository.findByRevieweeId(userId);
        return reviewMapper.toDtoList(reviews);
    }
}
