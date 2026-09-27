package com.yyy.sideproject.repository;

import com.yyy.sideproject.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}