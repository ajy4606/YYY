package com.yyy.sideproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    
    private String searchType;
    private String keyword;
    
    //이메일 변경시 새 이메일 필드
    private String newEmail;
    //비밀번호 변경시 새 비밀번호 필드
    private String newPassword;
}