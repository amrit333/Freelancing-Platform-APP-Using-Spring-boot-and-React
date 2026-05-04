package com.freelancing.platform.repository;

import com.freelancing.platform.entity.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BidRepository extends MongoRepository<Bid, String> {
    List<Bid> findByProjectId(String projectId);
    List<Bid> findByFreelancerId(String freelancerId);
}
