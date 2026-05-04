package com.freelancing.platform.controller;

import com.freelancing.platform.dto.request.ContractAcceptRequest;
import com.freelancing.platform.dto.response.ContractDto;
import com.freelancing.platform.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/accept")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ContractDto> acceptBid(@Valid @RequestBody ContractAcceptRequest request, Authentication authentication) {
        ContractDto contractDto = contractService.acceptBid(request, authentication.getName());
        return new ResponseEntity<>(contractDto, HttpStatus.CREATED);
    }
}
