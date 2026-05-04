package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.response.ContractDto;
import com.freelancing.platform.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractMapper {
    @Mapping(source = "project.id", target = "projectId")
    ContractDto toDto(Contract contract);
    List<ContractDto> toDtoList(List<Contract> contracts);
}
