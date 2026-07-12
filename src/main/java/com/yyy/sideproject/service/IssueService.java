package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyy.sideproject.domain.Issue;
import com.yyy.sideproject.dto.IssueRequest;
import com.yyy.sideproject.mapper.IssueMapper;
import com.yyy.sideproject.repository.IssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper; // 💡 상단에 Mapper 의존성 주입 추가 필요! (JPA 밑에 적어주세요)
    
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
 // 🌟 새로 추가할 부분: 특정 과제 1개 상세 조회
    public Issue getIssueById(Long id) {
        // findById는 결과가 없을 수도 있기 때문에(Optional), 없을 경우의 예외 처리를 함께 해줍니다.
        return issueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다. ID: " + id));
    }    
 // 새로 추가할 검색 기능
    public List<Issue> searchIssues(String title, String status) {
        return issueMapper.findIssuesByCondition(title, status);
    }
    
}