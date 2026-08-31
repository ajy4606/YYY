package com.yyy.sideproject;

import static org.assertj.core.api.Assertions.assertThat;

import com.yyy.sideproject.domain.UserJdbcEntity;
import com.yyy.sideproject.domain.UserJpaEntity;
import com.yyy.sideproject.repository.AdminRepository;
import com.yyy.sideproject.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;

// 실제 설정의 DB에 접속하는 읽기 전용 통합 테스트. DDL/초기화 SQL은 실행하지 않는다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
    "spring.jpa.hibernate.ddl-auto=none",
    "spring.sql.init.mode=never",
    "spring.main.allow-bean-definition-overriding=false"
})
class UserPersistenceIntegrationTest {
    @Autowired ApplicationContext context;
    @Autowired JdbcMappingContext jdbcMappingContext;
    @Autowired EntityManagerFactory entityManagerFactory;
    @Autowired AdminRepository adminRepository;
    @Autowired UserRepository userRepository;

    @Test
    void jpaAndJdbcUseSeparateModelsAndCanReadUsers() {
        assertThat(context.getBeansOfType(UserRepository.class)).hasSize(1);
        assertThat(context.getBeansOfType(AdminRepository.class)).hasSize(1);
        assertThat(jdbcMappingContext.getRequiredPersistentEntity(UserJdbcEntity.class)
            .getRequiredIdProperty().getName()).isEqualTo("id");
        assertThat(entityManagerFactory.getMetamodel().entity(UserJpaEntity.class)).isNotNull();

        // 저장/수정/삭제 없이 양쪽 Repository의 조회와 JDBC 컬럼 매핑을 확인한다.
        assertThat(userRepository.count()).isNotNegative();
        assertThat(adminRepository.selectUserAll()).allSatisfy(user ->
            assertThat(user.getId()).isNotNull());
    }
}
