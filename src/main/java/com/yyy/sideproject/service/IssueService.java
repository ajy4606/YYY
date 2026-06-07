package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyy.sideproject.domain.Issue;
import com.yyy.sideproject.dto.IssueRequest;
import com.yyy.sideproject.repository.IssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    @Transactional
    public Long registerIssue(IssueRequest request) {
        Issue issue = new Issue();
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setSeverity(request.getSeverity());
        issue.setReporterId(request.getReporterId());
        issue.setAssigneeId(request.getAssigneeId());
        
        // JPA를 이용한 INSERT 처리
        Issue savedIssue = issueRepository.save(issue);
        return savedIssue.getId();
    }
 // 🌟 새로 추가할 부분: 전체 목록 조회
    public List<Issue> getAllIssues() {
        // JPA가 기본 제공하는 findAll() 메서드로 DB의 모든 ISSUES 데이터를 가져옵니다.
        return issueRepository.findAll();
    }
    
}