package org.example.bebra.config;

import org.example.bebra.sochniy.Car;
import org.example.bebra.sochniy.Employee;
import org.example.bebra.sochniy.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bebra.sochniy")
public class EmployeeConf {

}
