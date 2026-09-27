package com.yyy.sideproject.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yyy.sideproject.domain.Faq;
import com.yyy.sideproject.dto.UserResponse;
import com.yyy.sideproject.repository.sql.FaqSql;


@Repository
public interface FaqRepository  extends CrudRepository<Faq,Long> {
	
	@Query(FaqSql.SELECT_USER_ID)
	UserResponse selectUserbyId(Long user_id);
	
	@Modifying
	@Query(FaqSql.INSERT_FAQ)
	public abstract int insertFaq(	@Param("user_id") Long user_id,
									@Param("category") String category,
									@Param("title") String title,
									@Param("author") String author,
									@Param("content") String content,
									@Param("is_secret") boolean is_secret,
									@Param("password") String password	);
	
	@Query(FaqSql.SELECT_FAQ_ALL)
	public abstract List<Faq> selectFaqAll(); 
	
	@Query(FaqSql.SELECT_FAQ_ID)
	public abstract Faq selectFaqbyId(Long id);
	


}
