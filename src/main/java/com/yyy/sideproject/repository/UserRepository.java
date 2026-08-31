package com.yyy.sideproject.repository;

import com.yyy.sideproject.domain.UserJpaEntity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
	// 1. ID 내림차순(최신순)으로 정렬해서 가져오기
    //List<UserJpaEntity> findAllByOrderByIdDesc();

    // 2. 이름 오름차순(가나다순)으로 정렬해서 가져오기
    List<UserJpaEntity> findAllByOrderByIdAsc();
    
    // 3. 생성일(regDate) 내림차순으로 정렬해서 가져오기
    //List<UserJpaEntity> findAllByOrderByRegDateDesc();
}
