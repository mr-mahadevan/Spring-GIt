package com.zexi.ennada.configuration;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.Encoder;

@Configuration
public class WebConfiguration {

    @Bean
    public Encoder encryption(){
        return new Encoder();
    }
}
