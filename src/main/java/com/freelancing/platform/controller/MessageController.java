package com.freelancing.platform.controller;

import com.freelancing.platform.dto.request.MessageRequest;
import com.freelancing.platform.dto.response.MessageDto;
import com.freelancing.platform.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@Valid @RequestBody MessageRequest request, Authentication authentication) {
        MessageDto sentMessage = messageService.sendMessage(request, authentication.getName());
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MessageDto>> getConversation(@PathVariable String userId, Authentication authentication) {
        return ResponseEntity.ok(messageService.getConversation(userId, authentication.getName()));
    }
}
