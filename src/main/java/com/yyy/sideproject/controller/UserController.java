package com.yyy.sideproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.sideproject.domain.User;
import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.repository.UserRepository;
import com.yyy.sideproject.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
	// 1. DB에 접근할 수 있는 리포지토리를 연결(주입)합니다.
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/signUp") 
 	public String signUpPage() {

    	return "signUp";  
	}

    @PostMapping("/signUp")
    public String createUser(UserRequest request) {
    	try {
    		userService.createUser(request);
    	} catch (ObjectOptimisticLockingFailureException e) {
    	    throw new RuntimeException("데이터가 최신 상태가 아닙니다. 새로고침 후 다시 시도해주세요.");
    	}

        return "redirect:/login";
    }

    // 로그인 페이지 (HTML 반환)
    @GetMapping("/login")
    public String loginPage() {
        return "loginView"; 
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    
	
	 
    
    @GetMapping("/members")
    public String showMemberList(Model model) {
    	
    	// 2. 가짜 데이터 대신 DB에서 모든 사용자(User)를 가져옵니다.
        //List<User> userList = userRepository.findAll();

        List<User> userList = userRepository.findAllByOrderByIdAsc();
        
        // 3. HTML(member_list.html)로 데이터를 보냅니다.
        // 이름표를 "members"로 유지하면 기존 HTML 코드를 안 고쳐도 됩니다.
        model.addAttribute("members", userList);

        // 3. templates 폴더 아래의 member-list.html 을 화면에 띄워라!
        return "member_list"; 
    }
}