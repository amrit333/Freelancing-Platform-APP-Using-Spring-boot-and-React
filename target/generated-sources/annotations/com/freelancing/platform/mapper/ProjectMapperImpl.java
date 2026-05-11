package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.ProjectRequest;
import com.freelancing.platform.dto.response.ProjectDto;
import com.freelancing.platform.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-11T09:21:37+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ProjectDto toDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setBudget( project.getBudget() );
        projectDto.setClient( userMapper.toDto( project.getClient() ) );
        projectDto.setCreatedAt( project.getCreatedAt() );
        projectDto.setDeadline( project.getDeadline() );
        projectDto.setDescription( project.getDescription() );
        projectDto.setId( project.getId() );
        projectDto.setStatus( project.getStatus() );
        projectDto.setTitle( project.getTitle() );

        return projectDto;
    }

    @Override
    public List<ProjectDto> toDtoList(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectDto> list = new ArrayList<ProjectDto>( projects.size() );
        for ( Project project : projects ) {
            list.add( toDto( project ) );
        }

        return list;
    }

    @Override
    public Project toEntity(ProjectRequest projectRequest) {
        if ( projectRequest == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.budget( projectRequest.getBudget() );
        project.deadline( projectRequest.getDeadline() );
        project.description( projectRequest.getDescription() );
        project.title( projectRequest.getTitle() );

        return project.build();
    }
}
