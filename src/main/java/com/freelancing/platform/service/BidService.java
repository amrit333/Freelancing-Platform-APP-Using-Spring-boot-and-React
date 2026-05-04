package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.BidRequest;
import com.freelancing.platform.dto.response.BidDto;
import com.freelancing.platform.entity.Bid;
import com.freelancing.platform.entity.Project;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.exception.BadRequestException;
import com.freelancing.platform.exception.ResourceNotFoundException;
import com.freelancing.platform.mapper.BidMapper;
import com.freelancing.platform.repository.BidRepository;
import com.freelancing.platform.repository.ProjectRepository;
import com.freelancing.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final BidMapper bidMapper;

    public BidDto createBid(BidRequest bidRequest, String email) {
        User freelancer = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = projectRepository.findById(bidRequest.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (!project.getStatus().name().equals("OPEN")) {
            throw new BadRequestException("Project is not open for bidding");
        }

        Bid bid = bidMapper.toEntity(bidRequest);
        bid.setFreelancer(freelancer);
        bid.setProject(project);
        bid.prePersist();

        Bid savedBid = bidRepository.save(bid);
        return bidMapper.toDto(savedBid);
    }

    public List<BidDto> getBidsForProject(String projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found");
        }
        List<Bid> bids = bidRepository.findByProjectId(projectId);
        return bidMapper.toDtoList(bids);
    }
}
