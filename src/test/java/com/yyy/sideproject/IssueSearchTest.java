package com.yyy.sideproject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class IssueSearchTest {

    @Test
    public void testIssueSearchFeature() {
        // 주의: 이 테스트를 실행하기 전에 반드시 스프링 부트 서버가 켜져 있어야 합니다!
        
        // 1. Playwright 브라우저 로봇을 켭니다 (false로 하면 실제 눈에 보이게 크롬 창이 뜹니다)
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            System.out.println("🚀 테스트 시작: 과제 목록 페이지로 이동합니다.");
            // 2. 우리가 만든 목록 페이지로 이동
            page.navigate("http://localhost:8081/issues/list");

            System.out.println("⌨️ 검색어 입력 중...");
            // 3. name 속성이 'title'인 input 박스에 "자동화" 라고 타이핑합니다.
            page.fill("input[name='title']", "자동화");

            // 4. 검색 버튼을 클릭합니다. (버튼의 텍스트가 '검색'인 것을 찾음)
            page.click("button:has-text('검색')");

            // 5. 검색이 완료되고 화면이 새로고침 될 때까지 잠시 기다립니다.
            page.waitForLoadState();

            System.out.println("🔍 검색 결과 검증 중...");
            // 6. 테이블(표) 안에 있는 모든 텍스트를 가져와서 "자동화"라는 단어가 있는지 확인(Assert)합니다.
            String tableText = page.innerText("table");
            
            // 검증 로직: 검색 결과에 우리가 원하는 텍스트가 포함되어 있어야 테스트 통과!
            assertTrue(tableText.contains("자동화"), "테스트 실패: 테이블에 '자동화'라는 과제가 없습니다!");

            System.out.println("✅ 테스트 성공! 3초 후 브라우저가 닫힙니다.");
            // 결과를 눈으로 볼 수 있게 3초 대기 후 종료
            page.waitForTimeout(3000); 
        }
    }
}