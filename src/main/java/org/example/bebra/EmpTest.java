package org.example.bebra;


import org.example.bebra.config.EmployeeConf;
import org.example.bebra.sochniy.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmpTest {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(EmployeeConf.class);
        Employee emp1 = context.getBean(Employee.class);
        Employee emp2 = context.getBean(Employee.class);
        System.out.println(emp1.getName());
        System.out.println(emp2.getName());
        emp1.setName("Oleg");
        System.out.println(emp1.getName());
        System.out.println(emp2.getName());
        emp1.destroy();
        emp2.destroy();
        context.close();
    }
}
