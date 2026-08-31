package com.yyy.sideproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yyy.sideproject.dto.UserRequest;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// 로그인 화면 이동
	@GetMapping("/login")
	public String loginPage() {
		return "loginView";
	}
	
	// 로그인 인증 처리
	@PostMapping("/login")
	@ResponseBody // 💡 알림창 스크립트를 브라우저에 바로 전달하기 위해 추가합니다!
	public String loginProcess(@RequestParam("id") Long id, @RequestParam("password") String password,
			HttpSession session) {

		System.out.println("controller 로그인 id: " + id);
		
		// 1. [체크] 우선 해당 ID(회원번호)를 가진 유저가 DB에 존재하는지 확인
		// 💡 내 userService에 id로 유저가 있는지 체크하는 메서드가 없다면 아래 주석처럼 구현하셔야 합니다.
		// boolean isIdExist = userService.existsById(id); 
		boolean isIdExist = (userService.findById(id) != null); // 예시 코드 (실제 메서드로 매핑하세요)

		if (!isIdExist) {
			// [경우의 수 1] 계정이 아예 없는 경우
			return "<script>" +
				   "alert('아이디 또는 비밀번호가 존재하지 않습니다.');" +
				   "location.href='/login';" + // 확인 누르면 로그인 페이지로 새로고침(이동)
				   "</script>";
		}

		// 2. [체크] 아이디는 존재하므로, 비밀번호를 검증합니다.
		UserResponse user = userService.login(id, password);
		System.out.println("controller 로그인user : " + user);

		if (user == null) {
			// [경우의 수 2] 아이디는 있지만 비밀번호가 틀린 경우 (user가 null)
			return "<script>" +
				   "alert('비밀번호가 일치하지 않습니다. 5회 틀릴 경우 계정이 잠깁니다.');" +
				   "location.href='/login';" + // 확인 누르면 로그인 페이지로 새로고침(이동)
				   "</script>";
		}

		// 3. [성공] 로그인 인증 성공 로직
		session.setMaxInactiveInterval(3600);
		session.setAttribute("loginUser", user);
		session.setAttribute("loginUserId", id);
		
		System.out.println("로그인 성공. 메인 이동/사용자 이름: " + user.getName());
		
		// 로그인 성공 시에는 자바스크립트로 메인 화면으로 보내줍니다.
		return "<script>location.href='/main';</script>";
	}

//	// 로그인 인증 처리
//	@PostMapping("/login")
//	@ResponseBody
//	public String loginProcess(@RequestParam("id") Long id, @RequestParam("password") String password,
//			HttpSession session, // 로그인 상태 기억하는 세션
//			Model model) {
//
//		System.out.println("controller 로그인 id: " + id);
//
//		System.out.println("controller 로그인login : " + userService.login(id, password));
//		UserResponse user = userService.login(id, password);
//		System.out.println("controller 로그인user : " + user);
//
//		if (user != null) {
//			// 로그인 세션 유지 시간 (초 단위) 마우스와 페이지 이동 없을때 세션 끊김
//			session.setMaxInactiveInterval(3600);
//
//			session.setAttribute("loginUser", user);
//			session.setAttribute("loginUserId", id);
//			System.out.println("로그인 성공. 메인 이동/사용자 이름: " + user.getName());
//			return "redirect:/main";
//		} else {
//			model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
//			return "loginView";
//		}
//	}

	// 사용자 목록
	@GetMapping("/users")
	public String getUserList(UserResponse userSearch, Model model) {

		if (userSearch == null) {
			userSearch = new UserResponse();
		}

		List<UserResponse> list = userService.getUserList(userSearch);

		model.addAttribute("userList", list);

		return "user_List";
	}

	// 회원가입
	@GetMapping("/signUp")
	public String signUpPage() {
		return "signUp";
	}

	@PostMapping("/saveUser")
	public String saveUserProcess(UserRequest userRequest) {
		userService.createUser(userRequest);

		return "redirect:/users";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 지움.
		System.out.println("로그아웃");
		return "redirect:/main";
	}

	/**
	 * 1. 마이페이지 메인 화면 진입 주소: http://localhost:8080/users/mypage
	 */
	@GetMapping("/mypage/mypage")
	public String myPageMain(HttpServletRequest request, Model model) {
		// 기존 세션 가져오기
		HttpSession session = request.getSession(false);

		// 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
		if (session == null || session.getAttribute("loginUser") == null) {
			return "redirect:/login";
		}

		// 세션에서 유저 정보를 꺼내서 HTML에 'user'라는 이름으로 전달
		Object loginUser = session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);

		return "mypage/mypage";
	}

	/**
     * 2. 이메일 변경 화면 요청 (HTML 조각 리턴)
     */
	@GetMapping("/mypage/chg_email")
    public String changeEmailForm() {
		return "mypage/chg_email";
    }

	/**
     * 3. 이메일 변경 기능 처리 (POST)
     */
	@PostMapping("/users/mypage/chg_email")
    public String changeEmail(@RequestParam("email") String email, RedirectAttributes redirectAttributes, Model model) {
        try {
            Long loginUserId = (long) 123; 
            userService.changeEmail(loginUserId, email);
            
            model.addAttribute("msg", "이메일이 정상적으로 변경되었습니다.");
            // 💡 URL 뒤에 마이페이지 탭을 식별할 쿼리 스트링을 붙여줍니다.
            model.addAttribute("url", "/main?tab=mypage"); 
            return "common/alert";
            
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users/mypage/chg_email";
        }
    }

    /**
     * 4. 비밀번호 변경 화면 요청 (HTML 조각 리턴)
     */
    @GetMapping("/mypage/chg_password")
    public String changePasswordForm(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        return "mypage/chg_password"; // templates/mypage/change-password.html
    }

    /**
     * 5. 비밀번호 변경 기능 처리 (일반 Form POST 전송)
     */
    @PostMapping("/users/mypage/chg_password") 
    public String changePassword(@ModelAttribute UserRequest userRequest, HttpServletRequest request, Model model) {
        try {
            // 1. 세션에서 로그인 회원 정보 조회
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                return "redirect:/login";
            }
            
            UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
            Long currentUserId = loginUser.getId(); 
            
            // 2. 서비스 레이어 호출 (비밀번호 검증 및 변경 SQL 실행)
            userService.changePassword(currentUserId, userRequest);
            
            // 3. 💡 [성공 시] 팝업창을 띄우고 로그인 페이지로 이동시키는 스크립트용 템플릿 리턴
            model.addAttribute("msg", "비밀번호가 변경되었습니다. 다시 로그인해주세요.");
            model.addAttribute("url", "/login"); 
            return "common/alert";

        } catch (Exception e) {
            System.out.println("❌ 비즈니스 로직 예외 발생: " + e.getMessage());
            // 현재 비밀번호 불일치 등의 에러가 나면, 메시지를 안고 비밀번호 변경 페이지로 되돌아갑니다.
            model.addAttribute("errorMessage", e.getMessage());
            return "mypage/chg_password"; 
        }
    }
    
    /**
     * 비밀번호 찾기 화면 이동
     */
    @GetMapping("/find_password")
    public String findPasswordForm() {
        return "find-password"; 
    }

    /**
     * 비밀번호 찾기 처리 (POST)
     */
    @PostMapping("/find_password")
    @ResponseBody
    public java.util.Map<String, Object> findPassword(String id, String email) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        
        // 1. TODO: 서비스/레포지토리를 통해 아이디와 이메일이 동시에 일치하는 유저가 있는지 확인
        // User user = userService.findByIdAndEmail(id, email);
        
        // 임시 테스트용 조건 (실제 프로젝트에서는 DB 조회로 대체하세요)
        boolean isExist = true; 

        if (!isExist) {
            response.put("status", "FAIL");
            response.put("message", "입력하신 정보와 일치하는 회원이 없습니다.");
            return response;
        }

        // 2. 랜덤한 임시 비밀번호 생성 (8자리 문자열)
        String tempPassword = java.util.UUID.randomUUID().toString().substring(0, 8);

        // 3. TODO: DB에 해당 유저의 비밀번호를 이 임시 비밀번호로 암호화해서 업데이트
        // userService.updatePassword(id, tempPassword);

        // 4. 결과 반환
        response.put("status", "SUCCESS");
        response.put("tempPassword", tempPassword); // 💡 이메일 연동 전 가시적 확인용
        return response;
    }

	/**
	 * 4. 회원 탈퇴 처리
	 */
	@GetMapping("/mypage/withdraw")
	public String withdraw(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("loginUser") != null) {
			// TODO: 서비스 로직 연동 (예: userService.deleteUser(...))

			// 탈퇴 즉시 세션 무효화(로그아웃)
			session.invalidate();
		}

		return "redirect:/main";
	}

}