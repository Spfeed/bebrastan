package org.example.bebra.sochniy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


@Setter
@Getter
@Component
@PropertySource("application.yml")
@NoArgsConstructor
public class Pet {
    @Value("${pet.name}")
    private String name;
    @Value("${pet.kind}")
    private String kind;
}
