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
public class UserJdbcEntity {

    @Id
    @Column("ID")
    private Long id;
    
    @Column("NAME")
    private String name;
    
    @Column("EMAIL")
    private String email;
    
    @Column("PASSWORD")
    private String password;
    
    @Column("ROLE")
    private String role;
    
    @Column("CREATED_AT")
    private LocalDateTime createdAt;
    
    @Column("CHANGE_DT")
    private LocalDateTime changeDt;
    
    public UserJdbcEntity(Long id, String name, String email, String password, String role, LocalDateTime changeDt) {
    	this.id = id;
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.role = role;
    	this.createdAt = LocalDateTime.now();
    	this.changeDt = changeDt;
    }
    
}
