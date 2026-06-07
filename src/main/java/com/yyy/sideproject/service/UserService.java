package com.yyy.sideproject.service;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    
    public List<UserResponse> getUserList(UserResponse userSearch) {
        return userMapper.srchUser(userSearch); 
    }

    public void createUser(UserRequest userRequest) {

    	userMapper.save(userRequest);  
    }

	/*
	 * public UserResponse login(Long id, String password) {
	 * 
	 * System.out.println("servise.login : "+id+" / "+password);
	 * 
	 * UserResponse searchParam = new UserResponse();
	 * 
	 * searchParam.setId(id); searchParam.setPassword("password");
	 * 
	 * System.out.println("servise.login.searchParam : "+searchParam);
	 * 
	 * List<UserResponse> users = userMapper.loginUser(searchParam);
	 * 
	 * if (!users.isEmpty()) { UserResponse user = users.get(0); if
	 * (user.getPassword().equals(password)) { return user; } } return null; }
	 */
    
}

