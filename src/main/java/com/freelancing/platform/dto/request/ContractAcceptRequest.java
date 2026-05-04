package com.freelancing.platform.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContractAcceptRequest {
    @NotNull
    private String bidId;
}
