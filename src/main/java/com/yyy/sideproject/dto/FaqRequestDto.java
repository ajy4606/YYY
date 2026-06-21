package com.yyy.sideproject.dto;

import com.yyy.sideproject.domain.Faq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FaqRequestDto {
	private String category;
	private String title;
	private String content;
	private Long userId;
	private boolean isSecret;
	private String password;

	// 작성자(author)는 신뢰할 수 없는 입력값 대신, userId로 검증/조회한 회원 이름을 주입받는다.
	public Faq toEntity(String author) {
		return Faq.builder()
				.category(this.category)
				.title(this.title)
				.content(this.content)
				.author(author)
				.isSecret(this.isSecret)
				.password(this.password)
				.build();
	}

}
