package com.yyy.sideproject.controller;

import com.yyy.sideproject.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor 
@RequestMapping("/issues/{issueId}/testcases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    // 엑셀 업로드 요청을 받는 곳
    @PostMapping("/upload")
    public String uploadTestCases(@PathVariable Long issueId,
                                  @RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
        try {
            // 1. 서비스 로직 호출: 과제 번호와 엑셀 파일을 던져줍니다.
            testCaseService.uploadExcelFile(issueId, file);
            
            // 2. 성공 메시지 담기 (화면에 잠깐 띄워줄 용도)
            redirectAttributes.addFlashAttribute("message", "테스트 케이스 엑셀 업로드가 완료되었습니다! 🎉");
            
        } catch (Exception e) {
            // 실패 시 에러 메시지 담기
            redirectAttributes.addFlashAttribute("errorMessage", "업로드 중 오류가 발생했습니다: " + e.getMessage());
        }
        
        // 3. 처리가 끝나면 원래 보던 '과제 상세 페이지'로 다시 튕겨냅니다(리다이렉트).
        // 주의: 기존 과제 상세 페이지 URL 패턴에 맞게 수정이 필요할 수 있습니다.
        return "redirect:/issues/" + issueId;
    }
}