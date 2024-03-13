package com.zexi.ennada.service;

import com.zexi.ennada.entity.User;
import com.zexi.ennada.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void CreateUserTest(){

        User user = new User();
        user.setFirstName("dev");
        user.setLastName("M");
        user.setEmail("123@email.com");
        user.setPassword("3drg45-23n5-32");
        user.setRole("USER");
        userRepo.save(user);

    }

}