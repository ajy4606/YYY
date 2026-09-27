package com.yyy.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    //로그인 후 메인화면
	@GetMapping("/main")
    public String mainPage(@RequestParam(value = "tab", required = false) String tab, Model model) {
        // ... 기존 메인 로직 ...
        
        model.addAttribute("activeTab", tab);
        
        return "mainView"; 
    }

//    // FAQ 화면
//    @GetMapping("/faq")
//    public String faqPage() {
//        return "faq";
//    }

    // 과제 페이지
    @GetMapping("/assignment")
    public String assignmentPage() {
        return "issue/list";
    }
}