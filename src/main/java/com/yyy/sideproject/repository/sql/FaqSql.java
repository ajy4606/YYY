package com.yyy.sideproject.repository.sql;


public class FaqSql {
	
	public static final String SELECT_USER_ID = """
			SELECT
				*
			 FROM USERS
			WHERE ID = :user_id
			""";
	
	public static final String SELECT_FAQ_ALL = """
			SELECT 
				 *
			  FROM FAQ
			""";
	
	public static final String SELECT_FAQ_ID = """
			SELECT
				*
			 FROM FAQ
			 WHERE ID = :id
			""";
	
	public static final String INSERT_FAQ = """
			INSERT INTO FAQ (
				ID
				,USER_ID
				,CATEGORY
				,TITLE
				,CONTENT
				,AUTHOR
				,IS_SECRET
				,PASSWORD
				,CREATED_AT
				,UPDATED_AT
			)		VALUES		(
				FAQ_SEQ.NEXTVAL
				,:user_id
				,:category
				,:title
				,:content
				,:author
				,:is_secret
				,:password
				, SYSDATE
				, NULL
			)
			""";

}




