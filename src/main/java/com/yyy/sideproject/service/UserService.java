package com.yyy.sideproject.service;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	
    private final PasswordEncoder passwordEncoder;

	public List<UserResponse> getUserList(UserResponse userSearch) {
		return userMapper.srchUser(userSearch);
	}

	public void createUser(UserRequest userRequest) {

		String encodedPassword =
	            passwordEncoder.encode(userRequest.getPassword());

	    userRequest.setPassword(encodedPassword);

	    userMapper.save(userRequest);
	}

	public UserResponse login(Long id, String password) {
		System.out.println("servise.login : "+id+" / "+password);
	  
//		UserResponse searchParam = new UserResponse();
//	  
//		searchParam.setId(id); 
//		searchParam.setPassword(password);
//	  
//		System.out.println("servise.login.searchParam : "+searchParam.toString());
//	  
//		List<UserResponse> users = userMapper.loginUser(searchParam);
//
//		System.out.println("servise.login.users : "+users.toString());
//		
//		if (!users.isEmpty()) {
//			UserResponse user = users.get(0); 
//			if(user.getPassword().equals(password)) {
//				return user; 
//			} 
//		}
		 UserResponse user = userMapper.findById(id);

		    if(user != null &&
		       passwordEncoder.matches(password, user.getPassword())) {

		        return user;
		    }
		return null;
	}
	
	// UserService.java (예시 구조)
	public UserResponse findById(Long id) {
	    // 레포지토리에서 id로 단건 조회하는 로직 (없으면 null 반환)
	    return userMapper.findById(id); 
	}
	
	/**
     * 현재 비밀번호 확인
     */
    public boolean checkPassword(Long userId, String currentPassword) {
        String encryptedPassword = userMapper.findPasswordById(userId);

        return passwordEncoder.matches( currentPassword, encryptedPassword );
    }

    /**
     * 비밀번호 변경
     */
    public void updatePassword(Long userId, String newPassword) {
        String encryptedPassword = passwordEncoder.encode(newPassword);


        userMapper.updatePassword( userId, encryptedPassword );
    }
	 
}
