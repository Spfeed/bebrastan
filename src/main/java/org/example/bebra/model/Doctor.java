package org.example.bebra.model;
import jakarta.persistence.*;
import org.example.bebra.sochniy.Pet;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "job")
    private String job;

    @OneToMany(mappedBy = "doctor")
    private List<Pet> pets;

    public Long getId() {return id;}

    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname=surname;}

    public String getJob() {return job;}

    public void setJob(String job) {this.job=job;}

}
