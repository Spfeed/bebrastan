package org.example.bebra.sochniy;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Scope("prototype")
@Setter
@Getter
@NoArgsConstructor
@PropertySource("application.yml")
public class Employee {
    @Value("${emp.name}")
    private String name;

    @Value("${emp.age}")
    private Integer age;

    @Value("${emp.salary}")
    private int salary;
    private Car car;
    private Pet pet;

    @PostConstruct
    public void init(){
        System.out.println("Class Employee: init method");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Class Employee: destroy method");
    }

    @Autowired
    public void setCar(Car car){
        this.car = car;
    }

    @Autowired
    public void setPet(Pet pet){
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Good morning, my name is" + name +  " I'm " + age + " years old" + " my pet is " + pet.getKind() + pet.getName() + " my car is " + car.getColor() + car.getMark();
    }
}
