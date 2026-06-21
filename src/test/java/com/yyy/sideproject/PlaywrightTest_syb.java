package com.yyy.sideproject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class PlaywrightTest_syb {
  public static void runPlaywrightTest(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("http://localhost:8081/login");
      page.locator("body").click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("계정이 없으신가요? 회원가입")).click();
      page.getByPlaceholder("이름").click();
      page.getByPlaceholder("이름").fill("일유빈");
      page.getByPlaceholder("아이디").click();
      page.getByPlaceholder("아이디").fill("12345");
      page.getByPlaceholder("example@email.com").click();
      page.getByPlaceholder("example@email.com").fill("12345@gmail.com");
      page.getByPlaceholder("비밀번호").dblclick();
      page.getByPlaceholder("비밀번호").fill("12345");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("가입하기")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("로그인 페이지로")).click();
      page.getByPlaceholder("아이디를 입력하세요").dblclick();
      page.getByPlaceholder("아이디를 입력하세요").fill("12345");
      page.getByPlaceholder("비밀번호를 입력하세요").click();
      page.getByPlaceholder("비밀번호를 입력하세요").fill("12345");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("로그인")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("회원 목록 보기")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("메인페이지")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("로그아웃")).click();
      page.getByPlaceholder("아이디를 입력하세요").click();
      page.getByPlaceholder("아이디를 입력하세요").fill("12345");
      page.getByPlaceholder("비밀번호를 입력하세요").click();
      page.getByPlaceholder("비밀번호를 입력하세요").fill("12345");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("로그인")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("FAQ")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("과제 페이지")).click();
      page.navigate("http://localhost:8081/main");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("로그아웃")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("회원목록 보기")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("메인페이지")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("로그인하러 가기")).click();
      page.getByPlaceholder("아이디를 입력하세요").fill("12");
      page.getByPlaceholder("아이디를 입력하세요").click();
      page.getByPlaceholder("아이디를 입력하세요").fill("12345");
      page.getByPlaceholder("비밀번호를 입력하세요").click();
      page.getByPlaceholder("비밀번호를 입력하세요").fill("12345");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("로그인")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("과제 페이지")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("+ 새 과제 등록")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("취소")).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("로그아웃")).click();
    }
  }
}