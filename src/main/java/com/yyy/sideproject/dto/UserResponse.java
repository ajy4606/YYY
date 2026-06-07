package com.yyy.sideproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    
    private String searchType;
    private String keyword;
}