package com.yyy.sideproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 화면 이동
    @GetMapping("/login")
    public String loginPage() {
        return "loginView"; 
    }

    // 로그인 인증 처리
    @PostMapping("/login")
    public String loginProcess(
            @RequestParam("id") Long id,
            @RequestParam("password") String password,
            HttpSession session, // 로그인 상태 기억하는 세션
            Model model) {
        
        System.out.println("controller 로그인 id: " + id);

        System.out.println("controller 로그인login : " + userService.login(id, password));
        UserResponse user = userService.login(id, password);
        System.out.println("controller 로그인user : " +user);
        
        if (user != null) {
            // 로그인 세션 유지 시간 (초 단위) 마우스와 페이지 이동 없을때 세션 끊김
            session.setMaxInactiveInterval(3600); 
            
            session.setAttribute("loginUser", user);
            System.out.println("로그인 성공. 메인 이동/사용자 이름: " + user.getName());
            return "redirect:/main"; 
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "loginView"; 
        }
    }

    //사용자 목록
    @GetMapping("/users")
    public String getUserList(UserResponse userSearch, Model model) {
    	
    	if (userSearch == null) {
            userSearch = new UserResponse();
        }
    	
        List<UserResponse> list = userService.getUserList(userSearch);

        model.addAttribute("userList", list);
        
        return "user_List";
    }

    //회원가입
    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUp";
    }

    @PostMapping("/saveUser")
    public String saveUserProcess(UserRequest userRequest) {
        userService.createUser(userRequest);

        return "redirect:/users";  
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 지움.
        System.out.println("로그아웃");
        return "redirect:/login";
    }

}