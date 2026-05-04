package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.ContractAcceptRequest;
import com.freelancing.platform.dto.response.ContractDto;
import com.freelancing.platform.entity.Bid;
import com.freelancing.platform.entity.Contract;
import com.freelancing.platform.entity.Project;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.entity.enums.BidStatus;
import com.freelancing.platform.entity.enums.ProjectStatus;
import com.freelancing.platform.exception.BadRequestException;
import com.freelancing.platform.exception.ResourceNotFoundException;
import com.freelancing.platform.mapper.ContractMapper;
import com.freelancing.platform.repository.BidRepository;
import com.freelancing.platform.repository.ContractRepository;
import com.freelancing.platform.repository.ProjectRepository;
import com.freelancing.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final BidRepository bidRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ContractMapper contractMapper;

    @Transactional
    public ContractDto acceptBid(ContractAcceptRequest request, String clientEmail) {
        User client = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Bid bid = bidRepository.findById(request.getBidId())
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found"));

        Project project = bid.getProject();

        if (!project.getClient().getId().equals(client.getId())) {
            throw new BadRequestException("You are not the owner of this project");
        }

        if (project.getStatus() != ProjectStatus.OPEN) {
            throw new BadRequestException("Project is not open for new contracts");
        }

        // Update bid status
        bid.setStatus(BidStatus.ACCEPTED);
        bidRepository.save(bid);

        // Update project status
        project.setStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);

        // Create contract
        Contract contract = Contract.builder()
                .project(project)
                .freelancer(bid.getFreelancer())
                .build();
        contract.prePersist();

        Contract savedContract = contractRepository.save(contract);
        return contractMapper.toDto(savedContract);
    }
}
