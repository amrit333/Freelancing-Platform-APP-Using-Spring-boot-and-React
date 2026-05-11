package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.response.ContractDto;
import com.freelancing.platform.entity.Contract;
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
public class ContractMapperImpl implements ContractMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ContractDto toDto(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractDto contractDto = new ContractDto();

        contractDto.setProjectId( contractProjectId( contract ) );
        contractDto.setCreatedAt( contract.getCreatedAt() );
        contractDto.setFreelancer( userMapper.toDto( contract.getFreelancer() ) );
        contractDto.setId( contract.getId() );
        contractDto.setStatus( contract.getStatus() );

        return contractDto;
    }

    @Override
    public List<ContractDto> toDtoList(List<Contract> contracts) {
        if ( contracts == null ) {
            return null;
        }

        List<ContractDto> list = new ArrayList<ContractDto>( contracts.size() );
        for ( Contract contract : contracts ) {
            list.add( toDto( contract ) );
        }

        return list;
    }

    private String contractProjectId(Contract contract) {
        if ( contract == null ) {
            return null;
        }
        Project project = contract.getProject();
        if ( project == null ) {
            return null;
        }
        String id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
