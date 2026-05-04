package com.freelancing.platform.repository;

import com.freelancing.platform.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends MongoRepository<Contract, String> {
    Optional<Contract> findByProjectId(String projectId);
    List<Contract> findByFreelancerId(String freelancerId);
}
