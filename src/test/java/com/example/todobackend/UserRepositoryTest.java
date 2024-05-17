//    package com.example.todobackend;
//
//    import com.example.todobackend.domain.User;
//    import com.example.todobackend.repository.UserRepository;
//    import org.junit.jupiter.api.Test;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//    import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//    import org.springframework.test.context.ContextConfiguration;
//    import org.springframework.test.context.TestPropertySource;
//    import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//    @ContextConfiguration(classes = TodoBackendApplication.class)
//    @DataJpaTest//(showSql = false)
//    @TestPropertySource(properties = {
//            "spring.test.database.replace=none",
//            "spring.sql.init.mode=always",
//            "spring.datasource.url=jdbc:tc:mysql:8.2.0:///test?TC_TMPFS=/var/lib/mysql:rw"
//    })
//    class UserRepositoryTest {
//
//        @Autowired
//        private TestEntityManager entityManager;
//
//        @Autowired
//        private UserRepository userRepository;
//
//        @Test
//        void saveUser() {
//            User expectedUser = createTestUser();
//            userRepository.save(expectedUser);
//
//            User retrievedUser = userRepository.findByUsernameIgnoreCase("bertil").orElse(null);
//
//            assert retrievedUser != null;
//            assertEquals(expectedUser.getUsername(), retrievedUser.getUsername());
//            assertEquals(expectedUser.getPassword(), retrievedUser.getPassword());
//            assertEquals(expectedUser.getRole(), retrievedUser.getRole());
//        }
//
//        private User createTestUser() {
//            User user = new User();
//            user.setUsername("bertil");
//            user.setPassword("1234");
//            user.setRole("USER");
//            return user;
//        }
//
//    }