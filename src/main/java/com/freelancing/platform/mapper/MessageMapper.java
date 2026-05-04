package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.MessageRequest;
import com.freelancing.platform.dto.response.MessageDto;
import com.freelancing.platform.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    MessageDto toDto(Message message);
    List<MessageDto> toDtoList(List<Message> messages);
    Message toEntity(MessageRequest messageRequest);
}
