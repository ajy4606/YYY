package com.yyy.sideproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "TEST_CASES")
@Getter @Setter
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // TC 고유 번호

    @Column(name = "issue_id", nullable = false)
    private Long issueId; // 속한 과제 번호

    @Column(name = "tc_category")
    private String tcCategory; // 카테고리 (예: 로그인, 결제)

    @Column(name = "tc_description", nullable = false, length = 1000)
    private String tcDescription; // 시나리오 내용

    @Column(name = "expected_result", length = 1000)
    private String expectedResult; // 기대 결과

    @Column(name = "status", length = 20)
    private String status = "UNTESTED"; // 상태 (기본값 설정)

    @Column(name = "tester_id")
    private Long testerId; // 테스트 수행자 ID

    @Column(name = "tested_at")
    private LocalDateTime testedAt; // 테스트 수행 일시

    // DB에서 자동으로 입력되도록 설정
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}