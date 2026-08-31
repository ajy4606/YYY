package com.yyy.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yyy.sideproject.dto.FaqRequestDto;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.service.FaqService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {
	
	private final FaqService faqService;
	
	@Transactional
	@PostMapping("/register")
	public String insertFaq(@ModelAttribute FaqRequestDto requestDto) {
		
		System.out.print(requestDto.getUser_id());
		
		/*UserResponse UserDTO*/
		UserResponse user = faqService.selectUserbyId(requestDto.getUser_id());
		
		if (user == null) {
			throw new IllegalArgumentException("존재하지 않는 ID 입니다 (" + requestDto.getUser_id() + ")");
		} else if (user.getName() == null) {
			throw new IllegalArgumentException("해당 ID(" + requestDto.getUser_id() + ")의 회원 이름이 존재하지 않습니다.");
		}
			
		requestDto.setAuthor(user.getName());
		
		System.out.print(requestDto.getCategory());
 		
		/*Spring Data JDBC에서 INSERT/UPDATE/DELETE를 @Query로 실행하려면 @Modifying이 필요하고, 
		  반환 타입도 void/int/boolean이어야 합니다. 지금은 애노테이션 없이 Long 반환이라 조회 쿼리로 취급됩니다.*/
		
		faqService.insertFaq(requestDto);
		
		return "redirect:/faq";
	}

	
	@GetMapping
	public String faqList(Model model) {
		
		model.addAttribute("faqs", faqService.selectFaqAll());
		
		return "faq";
	}
	
	@GetMapping("/{id}")
	public String faqDetail(@PathVariable Long id, Model model) {
		
		model.addAttribute("faq", faqService.selectFaqbyId(id));
		
		return "faqDetail";
	}


}



