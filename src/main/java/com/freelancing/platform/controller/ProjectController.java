package com.freelancing.platform.controller;

import com.freelancing.platform.dto.request.ProjectRequest;
import com.freelancing.platform.dto.response.ProjectDto;
import com.freelancing.platform.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectRequest projectRequest, Authentication authentication) {
        ProjectDto createdProject = projectService.createProject(projectRequest, authentication.getName());
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable String id, @Valid @RequestBody ProjectRequest projectRequest, Authentication authentication) {
        return ResponseEntity.ok(projectService.updateProject(id, projectRequest, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> deleteProject(@PathVariable String id, Authentication authentication) {
        projectService.deleteProject(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
