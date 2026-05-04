package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.BidRequest;
import com.freelancing.platform.dto.response.BidDto;
import com.freelancing.platform.entity.Bid;
import com.freelancing.platform.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-04T09:29:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class BidMapperImpl implements BidMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BidDto toDto(Bid bid) {
        if ( bid == null ) {
            return null;
        }

        BidDto bidDto = new BidDto();

        bidDto.setProjectId( bidProjectId( bid ) );
        bidDto.setId( bid.getId() );
        bidDto.setFreelancer( userMapper.toDto( bid.getFreelancer() ) );
        bidDto.setProposal( bid.getProposal() );
        bidDto.setAmount( bid.getAmount() );
        bidDto.setStatus( bid.getStatus() );
        bidDto.setCreatedAt( bid.getCreatedAt() );

        return bidDto;
    }

    @Override
    public List<BidDto> toDtoList(List<Bid> bids) {
        if ( bids == null ) {
            return null;
        }

        List<BidDto> list = new ArrayList<BidDto>( bids.size() );
        for ( Bid bid : bids ) {
            list.add( toDto( bid ) );
        }

        return list;
    }

    @Override
    public Bid toEntity(BidRequest bidRequest) {
        if ( bidRequest == null ) {
            return null;
        }

        Bid.BidBuilder bid = Bid.builder();

        bid.proposal( bidRequest.getProposal() );
        bid.amount( bidRequest.getAmount() );

        return bid.build();
    }

    private String bidProjectId(Bid bid) {
        if ( bid == null ) {
            return null;
        }
        Project project = bid.getProject();
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
