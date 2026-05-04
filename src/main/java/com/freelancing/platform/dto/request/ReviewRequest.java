package com.freelancing.platform.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotNull
    private String revieweeId;

    @NotNull
    private String projectId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;
}
