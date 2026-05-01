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
}