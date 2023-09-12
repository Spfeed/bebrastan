package org.example.bebra.config;

import org.example.bebra.sochniy.Car;
import org.example.bebra.sochniy.Employee;
import org.example.bebra.sochniy.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConf {
    @Bean
    public Pet pet(){
        return new Pet("Abobus", "mongrel");
    }

    @Bean
    public Car car(){
        return new Car("Lada", "black");
    }

    @Bean
    public Employee employee(){
        return new Employee("Danil",21,6000,car(),pet());
    }
}
