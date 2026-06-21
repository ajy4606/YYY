package com.yyy.sideproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "faq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String category;
	
	@Column(nullable = false, length = 250)
	private String title;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	
	@Column(nullable = false)
	private String author;
	//회원 테이블 나중에 조인여부 확인해보기
	
	@Column(nullable = false)
	private boolean isSecret;
	
	@Column(length = 100)
	private String password;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime updatedAt;
	
	
	@Builder
	public Faq(String category, String title, String content, String author, boolean isSecret, String password) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.author = author;
		this.isSecret = isSecret;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}	
	
}
