package org.example.bebra.sochniy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import jakarta.persistence.*;
import org.example.bebra.model.*;

import java.util.List;

@Setter
@Getter
@Component
@PropertySource("application.yml")
@NoArgsConstructor
@Entity
@Table(name = "pets")

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    //@Value("${pet.name}")
    private String name;

    @Column(name = "kind")
    //@Value("${pet.kind}")
    private String kind;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "pet")
    private List<MedicalHistory> medicalHistories;

}
