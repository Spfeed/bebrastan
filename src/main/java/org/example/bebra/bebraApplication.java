package org.example.bebra;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.bebra")
public class bebraApplication {
    public static void main(String[] args) {
        SpringApplication.run(bebraApplication.class, args);
    }
}
