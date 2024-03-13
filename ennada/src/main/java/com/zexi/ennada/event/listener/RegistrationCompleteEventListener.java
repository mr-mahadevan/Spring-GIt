package com.zexi.ennada.event.listener;

import com.zexi.ennada.entity.User;
import com.zexi.ennada.entity.VerificationToken;
import com.zexi.ennada.event.RegistrationCompleteEvent;
import com.zexi.ennada.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
            User user = event.getUser();
            String token = UUID.randomUUID().toString();
        //save verification token
        VerificationToken verificationToken = new VerificationToken(token,user);
        userService.saveVerificationToken(verificationToken);

        String url = event.getUrl() +
                "/verifyRegistration?token=" +
                token;

        //sending mail
        log.info("Click the link the activate the account " + url);

    }
}
