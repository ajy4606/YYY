package com.yyy.sideproject.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yyy.sideproject.domain.UserJdbcEntity;
import com.yyy.sideproject.repository.sql.AdminSql;

@Repository
public interface AdminRepository extends CrudRepository<UserJdbcEntity,Long> {
	
	@Query(AdminSql.SELECT_USERS_ALL)
	List<UserJdbcEntity> selectUserAll();
	
	@Query(AdminSql.SELECT_USER_COUNT)
	int selectUserCount();
	
	@Query(AdminSql.UPDATE_USER_PWD)
	int updateUserPwd(String userId);

}
