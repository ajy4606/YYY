package com.yyy.sideproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yyy.sideproject.domain.Faq;

@Repository
public interface FaqRepository  extends JpaRepository<Faq,Long> {

}
