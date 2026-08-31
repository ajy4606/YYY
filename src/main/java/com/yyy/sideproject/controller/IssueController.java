package com.yyy.sideproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yyy.sideproject.domain.Issue;
import com.yyy.sideproject.domain.TestCase;
import com.yyy.sideproject.dto.IssueRequest;
import com.yyy.sideproject.repository.TestCaseRepository;
import com.yyy.sideproject.service.IssueService;

import jakarta.servlet.http.HttpSession; // 💡 이 import 문이 반드시 있어야 합니다!
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final TestCaseRepository testCaseRepository;

    // 폼 화면을 보여주는 메서드 (여기에 세션 테스트 코드가 들어갔습니다)
    @GetMapping("/register")
    public String showRegisterForm(Model model, HttpSession session) {
        // 테스트용: 화면 접속 시 강제로 세션에 111번 유저 로그인 상태로 만듦
        session.setAttribute("loginUserId", 111L); 
        
        model.addAttribute("issueRequest", new IssueRequest());
        return "issue/register";
    }

    // 폼 데이터 제출을 처리하는 메서드
    @PostMapping("/register")
    public String registerIssue(@ModelAttribute IssueRequest issueRequest, HttpSession session) {
        
        // 세션에서 로그인한 유저 ID를 동적으로 가져옵니다.
        Long loggedInUserId = (Long) session.getAttribute("loginUserId");

        if (loggedInUserId == null) {
            return "redirect:/login"; 
        }

        // 동적으로 가져온 ID를 보고자(Reporter)로 세팅합니다.
        issueRequest.setReporterId(loggedInUserId); 
        
        issueService.registerIssue(issueRequest);
        
        return "redirect:/issues/list";
    }
    
 // 🌟 새로 추가할 부분: 목록 화면 처리
    @GetMapping("/list")
    public String showIssueList(
            @RequestParam(name = "title", required = false) String title, 
            @RequestParam(name = "status", required = false) String status, 
            Model model) {
        
        // 검색 조건(title, status)을 받아 MyBatis 동적 쿼리 서비스를 호출합니다.
        // 검색어가 없으면(null) 자동으로 전체 조회가 됩니다.
        List<Issue> issues = issueService.searchIssues(title, status);
        
        // 화면(HTML)에 데이터를 전달합니다.
        model.addAttribute("issues", issues);
        
        return "issue/list"; 
    }
    
 // 상세 조회 화면 처리
    @GetMapping("/{id}")
    public String showIssueDetail(@PathVariable("id") Long id, Model model) {
        // 1. Service를 통해 해당 ID의 과제 정보를 가져옵니다.
        Issue issue = issueService.getIssueById(id);
        
        // 2. 화면에서 쓸 수 있게 모델에 담습니다.
        model.addAttribute("issue", issue);
        
        // 🌟 3. 새롭게 추가된 로직: 이 과제(id)에 속한 TC 목록을 DB에서 가져와서 모델에 담기
        List<TestCase> testCases = testCaseRepository.findByIssueIdOrderByIdAsc(id);
        model.addAttribute("testCases", testCases);
        
        // 4. templates/issue 폴더 안의 detail.html을 보여줍니다.
        return "issue/detail";
    }
}