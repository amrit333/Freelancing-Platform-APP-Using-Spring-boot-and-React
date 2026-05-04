package com.freelancing.platform.repository;

import com.freelancing.platform.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByClientId(String clientId);
}
