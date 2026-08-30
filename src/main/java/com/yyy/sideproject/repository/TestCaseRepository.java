package com.yyy.sideproject.repository;

import com.yyy.sideproject.domain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    
    // 특정 과제(issueId)에 속한 TC 목록만 싹 가져오는 메서드 (나중에 화면에 뿌릴 때 사용)
    List<TestCase> findByIssueIdOrderByIdAsc(Long issueId);
}