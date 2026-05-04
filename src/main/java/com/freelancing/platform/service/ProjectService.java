package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.ProjectRequest;
import com.freelancing.platform.dto.response.ProjectDto;
import com.freelancing.platform.entity.Project;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.exception.ResourceNotFoundException;
import com.freelancing.platform.mapper.ProjectMapper;
import com.freelancing.platform.repository.ProjectRepository;
import com.freelancing.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    public ProjectDto createProject(ProjectRequest projectRequest, String email) {
        User client = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = projectMapper.toEntity(projectRequest);
        project.setClient(client);
        project.prePersist();

        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDtoList(projects);
    }

    public ProjectDto getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.toDto(project);
    }

    public ProjectDto updateProject(String id, ProjectRequest projectRequest, String email) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        
        if (!project.getClient().getEmail().equals(email)) {
            throw new com.freelancing.platform.exception.BadRequestException("You can only edit your own projects");
        }

        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());
        project.setBudget(projectRequest.getBudget());
        project.setDeadline(projectRequest.getDeadline());

        return projectMapper.toDto(projectRepository.save(project));
    }

    public void deleteProject(String id, String email) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        
        if (!project.getClient().getEmail().equals(email)) {
            throw new com.freelancing.platform.exception.BadRequestException("You can only delete your own projects");
        }

        projectRepository.delete(project);
    }
}
