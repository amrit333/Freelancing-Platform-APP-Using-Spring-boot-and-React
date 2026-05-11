package com.freelancing.platform.mapper;

import com.freelancing.platform.dto.request.MessageRequest;
import com.freelancing.platform.dto.response.MessageDto;
import com.freelancing.platform.entity.Message;
import com.freelancing.platform.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-11T09:21:37+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setSenderId( messageSenderId( message ) );
        messageDto.setReceiverId( messageReceiverId( message ) );
        messageDto.setContent( message.getContent() );
        messageDto.setId( message.getId() );
        messageDto.setTimestamp( message.getTimestamp() );

        return messageDto;
    }

    @Override
    public List<MessageDto> toDtoList(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public Message toEntity(MessageRequest messageRequest) {
        if ( messageRequest == null ) {
            return null;
        }

        Message.MessageBuilder message = Message.builder();

        message.content( messageRequest.getContent() );

        return message.build();
    }

    private String messageSenderId(Message message) {
        if ( message == null ) {
            return null;
        }
        User sender = message.getSender();
        if ( sender == null ) {
            return null;
        }
        String id = sender.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String messageReceiverId(Message message) {
        if ( message == null ) {
            return null;
        }
        User receiver = message.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        String id = receiver.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
