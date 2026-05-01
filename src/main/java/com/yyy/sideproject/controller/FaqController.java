package com.yyy.sideproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yyy.sideproject.dto.FaqRequestDto;
import com.yyy.sideproject.service.FaqService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {
	
	private final FaqService faqService;
	
	@GetMapping
	public String faqList(Model model) {
		model.addAttribute("faqs", faqService.getAllFaqs());
		return "faq";
	}
	
	@PostMapping("/register")
	public String createFaq(@ModelAttribute FaqRequestDto requestDto) {
		faqService.createFaq(requestDto);
		return "redirect:/faq";
	}

}
