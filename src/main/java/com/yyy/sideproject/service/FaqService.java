package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.yyy.sideproject.domain.Faq;
import com.yyy.sideproject.dto.FaqRequestDto;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.repository.FaqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    
    public UserResponse selectUserbyId(Long user_id) {
    	return faqRepository.selectUserbyId(user_id);
    }

    public int insertFaq(FaqRequestDto dto) {	
        return faqRepository.insertFaq(	dto.getUser_id(),
        								dto.getCategory(),
        								dto.getTitle(),
        								dto.getAuthor(),
        								dto.getContent(),
        								dto.is_secret(),
        								dto.getPassword());
    }
    
    public List<Faq> selectFaqAll() {
        return faqRepository.selectFaqAll();
    }

    public Faq selectFaqbyId(Long id) {
        return faqRepository.selectFaqbyId(id);
    }
}




