package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.ReviewRequest;
import com.freelancing.platform.dto.response.ReviewDto;
import com.freelancing.platform.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    @Mapping(source = "reviewer.id", target = "reviewerId")
    @Mapping(source = "reviewee.id", target = "revieweeId")
    @Mapping(source = "project.id", target = "projectId")
    ReviewDto toDto(Review review);

    List<ReviewDto> toDtoList(List<Review> reviews);
    Review toEntity(ReviewRequest reviewRequest);
}
