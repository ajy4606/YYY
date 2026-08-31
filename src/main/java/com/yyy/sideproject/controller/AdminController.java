package com.yyy.sideproject.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.sideproject.service.AdminService;

import lombok.RequiredArgsConstructor;

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
		return Map.of("count", adminservice.selectUserAll().size());
	}
	

}
