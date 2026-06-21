package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyy.sideproject.domain.Faq;
import com.yyy.sideproject.domain.User;
import com.yyy.sideproject.dto.FaqRequestDto;
import com.yyy.sideproject.repository.FaqRepository;
import com.yyy.sideproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

	private final FaqRepository faqRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long createFaq(FaqRequestDto requestDto) {

		// userId 검증 후, 매핑된 회원 이름을 작성자로 저장한다.
		User user = userRepository.findById(requestDto.getUserId())
				.orElseThrow(() -> new IllegalArgumentException(
						"존재하지 않는 회원입니다. id=" + requestDto.getUserId()));

		Faq faq = requestDto.toEntity(user.getName());

		Faq savedFaq = faqRepository.save(faq);

		return savedFaq.getId();
	}
	
	public List<Faq> getAllFaqs() {
		return faqRepository.findAll();
	}

	public Faq getFaq(Long id) {
		return faqRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다. id=" + id));
	}

}
