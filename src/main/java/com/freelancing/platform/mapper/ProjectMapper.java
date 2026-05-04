package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.ProjectRequest;
import com.freelancing.platform.dto.response.ProjectDto;
import com.freelancing.platform.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectDto toDto(Project project);
    List<ProjectDto> toDtoList(List<Project> projects);
    Project toEntity(ProjectRequest projectRequest);
}
