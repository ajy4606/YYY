package com.yyy.sideproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String password;
    
    @Column
    private String role;
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime changeDt;
    
    @Builder
    public User(Long id, String name, String email, String password, String role, LocalDateTime changeDt) {
    	this.id = id;
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.role = role;
    	this.createdAt = LocalDateTime.now();
    	this.changeDt = changeDt;
    }
    
}