package org.example.bebra;


import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.bebra")
//@EntityScan(basePackages = "org.example.bebra.sochniy")
public class bebraApplication {
    public static void main(String[] args) {
        SpringApplication.run(bebraApplication.class, args);
    }
}
