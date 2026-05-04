package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.BidRequest;
import com.freelancing.platform.dto.response.BidDto;
import com.freelancing.platform.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BidMapper {
    @Mapping(source = "project.id", target = "projectId")
    BidDto toDto(Bid bid);
    List<BidDto> toDtoList(List<Bid> bids);
    Bid toEntity(BidRequest bidRequest);
}
