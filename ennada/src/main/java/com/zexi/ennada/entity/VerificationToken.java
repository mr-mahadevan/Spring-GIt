package com.zexi.ennada.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    //Expiration Time
    private final static int EXPIRING_TIME_LIMIT = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "user_access_key"))
    private User user;

    private Date expiration_time;

    public VerificationToken(String token, User user){
        this.token = token;
        this.user = user;
        this.expiration_time = expirationTimeGenerator();
    }

    private Date expirationTimeGenerator() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, VerificationToken.EXPIRING_TIME_LIMIT);
        return new Date(calendar.getTime().getTime());
    }

    private VerificationToken(String token){
        super();
        this.token = token;
        this.expiration_time = expirationTimeGenerator();
    }

}
