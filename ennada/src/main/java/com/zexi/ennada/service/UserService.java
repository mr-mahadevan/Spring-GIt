package com.zexi.ennada.service;

import com.zexi.ennada.DTO.UserDTO;
import com.zexi.ennada.entity.User;
import com.zexi.ennada.entity.VerificationToken;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public User createUser(UserDTO userModel);

    public String verifyToken(String token);

    void saveVerificationToken(VerificationToken verificationToken);

    String resendVerificationToken(String email, String url);
}
