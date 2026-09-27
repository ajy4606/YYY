package com.yyy.sideproject.repository.sql;

public class AdminSql {
	
	public static final String SELECT_USERS_ALL = """
			 SELECT * FROM USERS
			""";
	
	public static final String SELECT_USER_COUNT = """
			SELECT COUNT(*) FROM USERS
			""";
	
	public static final String UPDATE_USER_PWD = """
			UPDATE USERS
			SET  PASSWORD = ""
				,LOGIN_FAIL_CNT = "0"
				,CHANGED_DT = SYSDATE
			WHERE USER_ID = :userId
			""";
	
}
