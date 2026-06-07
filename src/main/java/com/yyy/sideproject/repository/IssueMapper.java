package com.yyy.sideproject.repository;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface IssueMapper {
    // 추후 다중 검색 조건이 포함된 과제 목록 조회 시 사용
    List<Map<String, Object>> findIssueListWithConditions(Map<String, Object> params);
}