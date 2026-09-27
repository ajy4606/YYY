package com.yyy.sideproject.mapper;

import com.yyy.sideproject.domain.Issue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface IssueMapper {
    // @Param은 XML에서 사용할 변수 이름을 지정해줍니다.
    List<Issue> findIssuesByCondition(@Param("title") String title, @Param("status") String status);
}