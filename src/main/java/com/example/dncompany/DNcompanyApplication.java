package com.example.dncompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class DNcompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DNcompanyApplication.class, args);
    }

}
