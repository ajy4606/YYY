package com.yyy.sideproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FaqRequestDto {
	
	private Long user_id;
	private String category;
	private String title;
	private String content;
	private String author;
	private boolean is_secret;
	private String password;

}
