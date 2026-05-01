package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyy.sideproject.domain.Faq;
import com.yyy.sideproject.dto.FaqRequestDto;
import com.yyy.sideproject.repository.FaqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {
	
	private final FaqRepository faqRepository;
	
	@Transactional
	public Long createFaq(FaqRequestDto requestDto) {
		
		Faq faq = requestDto.toEntity();
		
		Faq savedFaq = faqRepository.save(faq);
		
		return savedFaq.getId();
	}
	
	public List<Faq> getAllFaqs() {
		return faqRepository.findAll();
	}
	
}
