package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.MessageRequest;
import com.freelancing.platform.dto.response.MessageDto;
import com.freelancing.platform.entity.Message;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.exception.ResourceNotFoundException;
import com.freelancing.platform.mapper.MessageMapper;
import com.freelancing.platform.repository.MessageRepository;
import com.freelancing.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    public MessageDto sendMessage(MessageRequest request, String senderEmail) {
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));

        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver not found"));

        Message message = messageMapper.toEntity(request);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.prePersist();

        Message savedMessage = messageRepository.save(message);
        return messageMapper.toDto(savedMessage);
    }

    public List<MessageDto> getConversation(String otherUserId, String currentUserEmail) {
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userRepository.existsById(otherUserId)) {
            throw new ResourceNotFoundException("Other user not found");
        }

        ObjectId currentId = new ObjectId(currentUser.getId());
        ObjectId otherId = new ObjectId(otherUserId);
        
        List<Message> messages = messageRepository.findConversation(currentId, otherId, Sort.by(Sort.Direction.ASC, "timestamp"));
        return messageMapper.toDtoList(messages);
    }
}
