package com.freelancing.platform.dto.response;

import com.freelancing.platform.entity.enums.ProjectStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private String id;
    private UserDto client;
    private String title;
    private String description;
    private BigDecimal budget;
    private LocalDate deadline;
    private ProjectStatus status;
    private LocalDateTime createdAt;
}
