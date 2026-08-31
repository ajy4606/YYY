package com.yyy.sideproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yyy.sideproject.domain.User;
import com.yyy.sideproject.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminRepository adminRepository;
	
	public List<User> selectUserAll() {
		return adminRepository.selectUserAll();
	}

}
