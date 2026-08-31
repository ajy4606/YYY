package com.yyy.sideproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Table("FAQ")
public class Faq {

	@Id
	@Column("ID")
	private Long id;
	
	@Column("USER_ID")
	private Long user_id;
	
	@Column("CATEGORY")
	private String category;
	
	@Column("TITLE")
	private String title;
	
	@Column("CONTENT")
	private String content;
	
	@Column("AUTHOR")
	private String author;
	//회원 테이블 나중에 조인여부 확인해보기
	
	@Column("IS_SECRET")
	private boolean isSecret;
	
	@Column("PASSWORD")
	private String password;
	
	@Column("CREATED_AT")
	private LocalDateTime createdAt;
	
	@Column("UPDATED_AT")
	private LocalDateTime updatedAt;
	
	
	@Builder
	public Faq(Long user_id, String category, String title, String content, String author, boolean isSecret, String password) {
		this.user_id = user_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.author = author;
		this.isSecret = isSecret;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}	
	
}
