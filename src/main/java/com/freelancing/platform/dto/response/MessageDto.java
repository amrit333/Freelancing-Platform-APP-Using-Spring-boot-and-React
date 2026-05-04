package com.freelancing.platform.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
}
