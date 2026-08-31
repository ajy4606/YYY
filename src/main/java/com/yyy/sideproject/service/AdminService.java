package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yyy.sideproject.domain.UserJdbcEntity;
import com.yyy.sideproject.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminRepository adminRepository;
	
	public List<UserJdbcEntity> selectUserAll() {
		return adminRepository.selectUserAll();
	}

}
