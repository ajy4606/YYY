package com.yyy.sideproject.mapper;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserMapper {
	List<UserResponse> srchUser(@Param("id") UserResponse userSearch);
    
    void save(UserRequest request);
    
	List<UserResponse> loginUser(@Param("id") UserResponse searchParam);

}