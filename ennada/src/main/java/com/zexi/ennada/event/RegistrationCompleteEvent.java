package com.zexi.ennada.event;

import com.zexi.ennada.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{

    private User user;

    private String url;

    public RegistrationCompleteEvent(User user, String url) {
        super(user);
        this.user = user;
        this.url = url;
    }
}
