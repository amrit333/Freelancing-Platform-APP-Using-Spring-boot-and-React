package com.freelancing.platform.entity;

import com.freelancing.platform.entity.enums.ContractStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    private String id;

    @DBRef
    private Project project;

    @DBRef
    private User freelancer;

    private ContractStatus status;

    private LocalDateTime createdAt;

    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = ContractStatus.ACTIVE;
        }
    }
}
