package com.yyy.sideproject.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Table("USERS")
public class User {

    @Id
    @Column("ID")
    private Long id;
    
    @Id
    @Column("NAME")
    private String name;
    
    @Id
    @Column("EMAIL")
    private String email;
    
    @Id
    @Column("PASSWORD")
    private String password;
    
    @Id
    @Column("ROLE")
    private String role;
    
    @Id
    @Column("CREATEDAT")
    private LocalDateTime createdAt;
    
    @Id
    @Column("CHAGNEDT")
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