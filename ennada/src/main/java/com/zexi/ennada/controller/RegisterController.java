package com.zexi.ennada.controller;


import com.zexi.ennada.DTO.UserDTO;
import com.zexi.ennada.entity.User;
import com.zexi.ennada.event.RegistrationCompleteEvent;
import com.zexi.ennada.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/api")
    public ResponseEntity<String> hello(){
        System.out.println("Request received");
        return ResponseEntity.ok("Hello World");
    }

    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO, HttpServletRequest request){
        System.out.println("Received Request");
        User user = userService.createUser(userDTO);

        applicationEventPublisher.publishEvent(
                new RegistrationCompleteEvent(user,
                applicationUrl(request)));
                return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/verifyRegistration", method = RequestMethod.GET)
    public ResponseEntity<String> verifyToken(@RequestParam("token") String token){
        String result = userService.verifyToken(token);
        if(result.equalsIgnoreCase("valid")){
            return ResponseEntity.ok("Verified Success");
        }

        return ResponseEntity.ok("Verification failed");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath();
    }

    @PostMapping(value = "resendVerificationToken")
    public ResponseEntity<String> resendVerificationToken(@RequestBody String email, HttpServletRequest request){
        String result = userService.resendVerificationToken(email,applicationUrl(request));
        return ResponseEntity.ok("Verification sent Successfully");
    }
}
