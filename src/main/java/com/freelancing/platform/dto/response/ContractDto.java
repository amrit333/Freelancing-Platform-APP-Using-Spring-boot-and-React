package com.freelancing.platform.dto.response;

import com.freelancing.platform.entity.enums.ContractStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractDto {
    private String id;
    private String projectId;
    private UserDto freelancer;
    private ContractStatus status;
    private LocalDateTime createdAt;
}
