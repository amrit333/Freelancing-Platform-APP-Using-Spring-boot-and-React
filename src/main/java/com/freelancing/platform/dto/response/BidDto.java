package com.freelancing.platform.dto.response;

import com.freelancing.platform.entity.enums.BidStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BidDto {
    private String id;
    private String projectId;
    private UserDto freelancer;
    private String proposal;
    private BigDecimal amount;
    private BidStatus status;
    private LocalDateTime createdAt;
}
