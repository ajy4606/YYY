package com.yyy.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	

}
