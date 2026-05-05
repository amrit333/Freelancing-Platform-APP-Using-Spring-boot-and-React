package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.ReviewRequest;
import com.freelancing.platform.dto.response.ReviewDto;
import com.freelancing.platform.entity.Project;
import com.freelancing.platform.entity.Review;
import com.freelancing.platform.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-05T09:40:16+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto toDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setReviewerId( reviewReviewerId( review ) );
        reviewDto.setRevieweeId( reviewRevieweeId( review ) );
        reviewDto.setProjectId( reviewProjectId( review ) );
        reviewDto.setId( review.getId() );
        reviewDto.setRating( review.getRating() );
        reviewDto.setComment( review.getComment() );
        reviewDto.setCreatedAt( review.getCreatedAt() );

        return reviewDto;
    }

    @Override
    public List<ReviewDto> toDtoList(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDto> list = new ArrayList<ReviewDto>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( toDto( review ) );
        }

        return list;
    }

    @Override
    public Review toEntity(ReviewRequest reviewRequest) {
        if ( reviewRequest == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.rating( reviewRequest.getRating() );
        review.comment( reviewRequest.getComment() );

        return review.build();
    }

    private String reviewReviewerId(Review review) {
        if ( review == null ) {
            return null;
        }
        User reviewer = review.getReviewer();
        if ( reviewer == null ) {
            return null;
        }
        String id = reviewer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String reviewRevieweeId(Review review) {
        if ( review == null ) {
            return null;
        }
        User reviewee = review.getReviewee();
        if ( reviewee == null ) {
            return null;
        }
        String id = reviewee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String reviewProjectId(Review review) {
        if ( review == null ) {
            return null;
        }
        Project project = review.getProject();
        if ( project == null ) {
            return null;
        }
        String id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
