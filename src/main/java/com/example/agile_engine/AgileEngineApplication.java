package com.example.agile_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class AgileEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileEngineApplication.class, args);
    }

}
