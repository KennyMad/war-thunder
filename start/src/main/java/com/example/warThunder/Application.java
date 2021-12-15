package com.example.warThunder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableTransactionManagement
@ComponentScan("com.example")
@EntityScan("com.example.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
