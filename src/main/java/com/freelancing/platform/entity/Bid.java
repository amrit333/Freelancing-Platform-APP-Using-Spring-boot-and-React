package com.freelancing.platform.entity;

import com.freelancing.platform.entity.enums.BidStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "bids")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {

    @Id
    private String id;

    @DBRef
    private Project project;

    @DBRef
    private User freelancer;

    private String proposal;

    private BigDecimal amount;

    private BidStatus status;

    private LocalDateTime createdAt;

    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = BidStatus.PENDING;
        }
    }
}
