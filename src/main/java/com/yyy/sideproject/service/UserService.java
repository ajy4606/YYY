package com.yyy.sideproject.service;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	  
		UserResponse searchParam = new UserResponse();
	  
		searchParam.setId(id); 
		searchParam.setPassword(password);
	  
		System.out.println("servise.login.searchParam : "+searchParam.toString());
	  
		List<UserResponse> users = userMapper.loginUser(searchParam);

		System.out.println("servise.login.users : "+users.toString());
		
		if (!users.isEmpty()) {
			UserResponse user = users.get(0); 
			// 💡 디버깅용 로그 2줄 추가!
			System.out.println("★ 화면에서 입력한 비밀번호: " + password);
			System.out.println("★ DB에서 꺼내온 비밀번호 필드값: " + user.getPassword());
			
			// 💡 [핵심 변환] equals 대신 passwordEncoder.matches()를 사용해 암호문을 비교합니다.
			// user.getPassword() -> DB에서 꺼내온 암호문 ($2a$10$...)
			// password -> 사용자가 로그인 창에 입력한 생짜 비밀번호 (1234)
			// 💡 user.getPassword().trim() 으로 수정하여 혹시 모를 공백을 지워줍니다.
			if (user.getPassword() != null && passwordEncoder.matches(password, user.getPassword().trim())) {
			    System.out.println("====== 로그인 최종 성공 ======");
			    return user; 
			} else {
				System.out.println("====== 로그인 실패: 비밀번호 불일치 ======");
			}
		} else {
			System.out.println("====== 로그인 실패: 해당 ID의 유저 없음 ======");
		}
		return null;
	}
	
	public UserResponse findById(Long id) {
	    return userMapper.findById(id); 
	}
	
	/**
	 * 비밀번호 변경 비즈니스 로직 (MyBatis - findPasswordById 활용 버전)
	 */
	@Transactional
	public void changePassword(Long id, UserRequest userRequest) {
		
		// 1. 💡 이미 만들어두신 findPasswordById 쿼리로 DB에 저장된 암호문 직접 조회
		String dbPassword = userMapper.findPasswordById(id);
		
		if (dbPassword == null) {
			throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
		}

		System.out.println("service1 (화면 입력): " + userRequest.getPassword());
		System.out.println("service2 (DB 암호문): " + dbPassword); // 이제 null이 아니라 암호문이 잘 찍힐 겁니다!

		// 2. 사용자가 입력한 '현재 비밀번호' 검증
		// userRequest.getPassword() -> 화면에서 입력한 1234
		// dbPassword -> DB에서 가져온 암호화된 비밀번호
		if (!passwordEncoder.matches(userRequest.getPassword(), dbPassword)) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
		}

		// 3. '새 비밀번호' 유효성 검사 (공백이나 빈 값 방지)
		if (userRequest.getNewPassword() == null || userRequest.getNewPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("새로운 비밀번호를 입력해주세요.");
		}
		
		// 4. 기존 비밀번호와 새 비밀번호가 같은지 검증
		if (passwordEncoder.matches(userRequest.getNewPassword(), dbPassword)) {
			throw new IllegalArgumentException("기존 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
		}

		// 5. 새 비밀번호 암호화
		String encryptedPassword = passwordEncoder.encode(userRequest.getNewPassword());
		
		// 6. MyBatis 처리를 위해 userRequest 객체에 값 세팅 후 DB 반영
		userRequest.setId(id); 
		userRequest.setPassword(encryptedPassword); 
		
		userMapper.updatePassword(userRequest); 
	}
	
	@Transactional
	public void changeEmail(Long id, String newEmail) {
		// 1. 값 유효성 체크
		if (newEmail == null || newEmail.trim().isEmpty()) {
			throw new IllegalArgumentException("이메일 주소를 올바르게 입력해주세요.");
		}
		
		// 💡 파라미터 묶기용 객체 생성 (UserRequest 재활용 또는 별도 DTO)
		UserRequest userRequest = new UserRequest();
		userRequest.setId(id);
		userRequest.setEmail(newEmail.trim());
		
		// 2. DB 업데이트 반영
		userMapper.updateEmail(userRequest);
	}
	 
}
