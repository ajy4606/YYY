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
	private String author;
	private boolean isSecret;
	private String password;
	
	public Faq toEntity() {
		return Faq.builder()
				.category(this.category)
				.title(this.title)
				.content(this.content)
				.author(this.author)
				.isSecret(this.isSecret)
				.password(this.password)
				.build();
	}

}
