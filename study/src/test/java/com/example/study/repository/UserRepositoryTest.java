package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
//        // String sql = insert into user () value (); 가아니라 오브젝트로 관리한다.
//        User user = new User();
//        // user.setId(10L); //자동으로 들어가므로 넣지 않는다.
//        user.setAccount("TestUser01");
//        user.setEmail("TestUser01@gmail.com");
//        user.setPhoneNumber("010-0000-0000");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("TestUser1");
//
//        User newUser = userRepository.save(user);
//        System.out.println("newUser: " +newUser);

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-0000-0000";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        assertEquals("Test01",newUser.getId());
    }

    @Test
    public void read() {
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-0000-0000");
        assertNotNull(user);
        assertEquals("Test01",user.getAccount());
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(5L);
        user.ifPresent( selectedUser -> {
            selectedUser.setAccount("pppp");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setUpdatedBy("update method()");

            userRepository.save(selectedUser);
        });
    }

    @Test
    // transactional은 쿼리를 실행해줘도 롤백을 시켜준다.
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        assertEquals(true, user.isPresent());

        user.ifPresent(selectedUser -> {
            userRepository.delete(selectedUser);
        });

        Optional<User> deletedUser = userRepository.findById(1L);

        if (deletedUser.isPresent()) {
            System.out.println("데이터 존재 : " +deletedUser);
        } else {
            System.out.println("데이터 삭제 성공");
        }

        assertEquals(deletedUser.isPresent(), false);

    }
}