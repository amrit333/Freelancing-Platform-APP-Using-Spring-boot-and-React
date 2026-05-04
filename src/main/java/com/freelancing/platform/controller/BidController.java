package com.freelancing.platform.controller;

import com.freelancing.platform.dto.request.BidRequest;
import com.freelancing.platform.dto.response.BidDto;
import com.freelancing.platform.service.BidService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;


    @PostMapping("/bids")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<BidDto> createBid(@Valid @RequestBody BidRequest bidRequest, Authentication authentication) {
        BidDto createdBid = bidService.createBid(bidRequest, authentication.getName());
        return new ResponseEntity<>(createdBid, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{id}/bids")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity<List<BidDto>> getProjectBids(@PathVariable("id") String projectId) {
        return ResponseEntity.ok(bidService.getBidsForProject(projectId));
    }
}
