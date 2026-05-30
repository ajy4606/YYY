package com.yyy.sideproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "ISSUES")
@Getter @Setter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob // CLOB 타입 매핑
    private String description;

    private String severity;
    private String status;

    @Column(name = "reporter_id")
    private Long reporterId; // USERS 테이블 FK

    @Column(name = "assignee_id")
    private Long assigneeId; // USERS 테이블 FK

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "OPEN"; // 기본 상태
        }
    }
}