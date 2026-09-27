package com.yyy.sideproject.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yyy.sideproject.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminservice;
	
	
	@GetMapping	
	public String userLists(Model model) {
		
		model.addAttribute("users", adminservice.selectUserAll());
		
		return "admin";
	}

	// AJAX 요청에는 HTML 뷰 이름 대신 JSON 데이터를 응답한다.
	@GetMapping("/user-count")
	@ResponseBody
	public Map<String, Integer> userCount() {
		// 학습용으로 기존 조회를 재사용한다. 데이터가 많아지면 COUNT 쿼리로 분리한다.
		
		int count = 0;
		
		try {
		
		count = adminservice.selectUserCount();
		
		} catch (Exception e) {
			log.error("조회 중에 에러 발생 : {}", e.getMessage(), e);
			throw e;
		}
		
		log.info("조회된 회원 수: {}", count);
		 
		return Map.of("count", count);
	}
	
	
	@PostMapping("/user-password-reset")
	@ResponseBody
	public ResponseEntity<String> userPwdReset(@SessionAttribute(name = "loginUserId", required = false) Long userId) {
		
		if(Objects.isNull(userId)) {
			log.warn("사용자의 ID가 없거나 공백입니다.");
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
		
		log.info("사용자 ID: {}", userId);
		int success = adminservice.updateUserPwd(String.valueOf(userId));
		
		if (success == 0) {
			log.warn("비밀번호 업데이트 에러 발생");
			throw new IllegalArgumentException("비밀번호 초기화에 에러가 발생했습니다.");
		}
		
		return ResponseEntity.ok("비밀번호 초기화에 성공하였습니다.");	
	}
	

}
