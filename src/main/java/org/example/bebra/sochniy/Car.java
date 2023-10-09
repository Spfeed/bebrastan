package org.example.bebra.sochniy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@PropertySource("application.yml")
@Component
@NoArgsConstructor
public class Car {
    @Value("${car.mark}")
    private String mark;

    @Value("${car.color}")
    private String color;


}



