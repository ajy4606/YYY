package com.yyy.sideproject.repository;

import com.yyy.sideproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}