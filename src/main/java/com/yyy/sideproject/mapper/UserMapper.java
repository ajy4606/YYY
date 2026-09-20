package com.yyy.sideproject.mapper;

import com.yyy.sideproject.dto.UserRequestDTO;
import com.yyy.sideproject.dto.UserResponse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserMapper {
	List<UserResponse> srchUser(@Param("id") UserResponse userSearch);

    void save(UserRequestDTO request);

	List<UserResponse> loginUser(@Param("id") UserResponse searchParam);
	
	//비밀번호 변경
	void updatePassword(UserRequestDTO userRequest);
	String findPasswordById(Long id);
	
	//이메일 변경
	void updateEmail(UserRequestDTO userRequest);

	UserResponse findById(Long id);

}