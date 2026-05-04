package com.freelancing.platform.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private String id;
    private String reviewerId;
    private String revieweeId;
    private String projectId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
