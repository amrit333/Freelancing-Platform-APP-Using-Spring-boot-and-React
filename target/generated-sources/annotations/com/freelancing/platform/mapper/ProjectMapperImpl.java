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
    date = "2026-05-05T09:40:16+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Microsoft)"
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

        projectDto.setId( project.getId() );
        projectDto.setClient( userMapper.toDto( project.getClient() ) );
        projectDto.setTitle( project.getTitle() );
        projectDto.setDescription( project.getDescription() );
        projectDto.setBudget( project.getBudget() );
        projectDto.setDeadline( project.getDeadline() );
        projectDto.setStatus( project.getStatus() );
        projectDto.setCreatedAt( project.getCreatedAt() );

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

        project.title( projectRequest.getTitle() );
        project.description( projectRequest.getDescription() );
        project.budget( projectRequest.getBudget() );
        project.deadline( projectRequest.getDeadline() );

        return project.build();
    }
}
