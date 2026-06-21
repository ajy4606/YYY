package com.yyy.sideproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueRequest {
    private String title;
    private String description;
    private String severity;
    private Long reporterId; // 실제로는 로그인 세션에서 가져올 수 있음
    private Long assigneeId;
}