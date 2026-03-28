package com.yyy.sideproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @GetMapping("/members")
    public String showMemberList(Model model) {
        // 1. 화면에 띄워볼 가짜 회원 데이터 2명 만들기
        List<MemberDto> members = new ArrayList<>();
        members.add(new MemberDto(1L, "김개발", "kim@spring.com"));
        members.add(new MemberDto(2L, "이코딩", "lee@boot.com"));

        // 2. "members"라는 이름표를 붙여서 데이터를 상자에 담기 (HTML로 전달)
        model.addAttribute("members", members);

        // 3. templates 폴더 아래의 member-list.html 을 화면에 띄워라!
        return "member_list"; 
    }

    // --- 데이터를 담을 간단한 그릇(DTO) 클래스 ---
    public static class MemberDto {
        private Long id;
        private String name;
        private String email;

        public MemberDto(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        // ★ 중요: 타임리프가 데이터를 읽어가려면 Getter가 꼭 있어야 합니다!
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}