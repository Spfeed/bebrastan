package org.example.bebra.sochniy;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private int salary;
    private Car car;
    private Pet pet;
}
