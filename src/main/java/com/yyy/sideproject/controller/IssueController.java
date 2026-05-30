package com.yyy.sideproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yyy.sideproject.domain.Issue;
import com.yyy.sideproject.dto.IssueRequest;
import com.yyy.sideproject.service.IssueService;

import jakarta.servlet.http.HttpSession; // 💡 이 import 문이 반드시 있어야 합니다!
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

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
    public String showIssueList(Model model) {
        // 1. Service를 통해 DB에서 모든 과제 목록을 가져옵니다.
        List<Issue> issues = issueService.getAllIssues();
        
        // 2. 화면(HTML)에서 사용할 수 있도록 "issues"라는 이름으로 담아줍니다.
        model.addAttribute("issues", issues);
        
        // 3. templates/issue 폴더 안의 list.html을 보여주도록 지시합니다.
        return "issue/list"; 
    }
}