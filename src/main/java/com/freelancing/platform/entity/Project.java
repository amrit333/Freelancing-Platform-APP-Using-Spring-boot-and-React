package com.freelancing.platform.entity;

import com.freelancing.platform.entity.enums.ProjectStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    private String id;

    @DBRef
    private User client;

    private String title;

    private String description;

    private BigDecimal budget;

    private LocalDate deadline;

    private ProjectStatus status;

    @DBRef
    @Builder.Default
    private List<Bid> bids = new ArrayList<>();

    private LocalDateTime createdAt;

    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = ProjectStatus.OPEN;
        }
    }
}
