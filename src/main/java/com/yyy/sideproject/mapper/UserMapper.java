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

	// FAQ 작성자 검증용: id로 회원 단건 조회 (없으면 null)
	UserResponse findById(@Param("id") Long id);
	
	//비밀번호 변경
	String findPasswordById( @Param("id") Long id );
    void updatePassword( @Param("id") Long id, @Param("password") String password );

}