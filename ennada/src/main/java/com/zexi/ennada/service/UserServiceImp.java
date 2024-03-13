package com.zexi.ennada.service;

import com.zexi.ennada.DTO.UserDTO;
import com.zexi.ennada.entity.User;
import com.zexi.ennada.entity.VerificationToken;
import com.zexi.ennada.repo.UserRepo;
import com.zexi.ennada.repo.VerificationTokenRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @Override
    public User createUser(UserDTO userModel){

        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setRole("USER");
        return userRepo.save(user);
    }



    @Override
    public String verifyToken(String token) {
       VerificationToken isAvailable =  verificationTokenRepo.findByToken(token);

       User user = isAvailable.getUser();
        Calendar calendar = Calendar.getInstance();
       if(isAvailable.getExpiration_time().getTime() - calendar.getTime().getTime() <= 0){
           return "invalid";
       }

       user.setActive(true);
       userRepo.save(user);

       //account activating
       return "Valid";
    }

    @Override
    public void saveVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepo.save(verificationToken);
    }

    @Override
    public String resendVerificationToken(String email, String url) {
        User user = userRepo.findByEmail(email);
        VerificationToken token = verificationTokenRepo.findByUserId(user.getId());

        log.info("Click to verify the token :" + url + "/verifyToken?" + token.getToken());

        return null;
    }

}
