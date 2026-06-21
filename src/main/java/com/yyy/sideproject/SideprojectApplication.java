package com.yyy.sideproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyy.sideproject.mapper")
public class SideprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SideprojectApplication.class, args);
	}

}
