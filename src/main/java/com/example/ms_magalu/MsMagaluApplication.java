package com.example.ms_magalu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsMagaluApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMagaluApplication.class, args);
    }

}
