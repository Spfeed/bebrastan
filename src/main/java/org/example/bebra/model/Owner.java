package org.example.bebra.model;
import jakarta.persistence.*;
import org.example.bebra.sochniy.Pet;

import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "fathername")
    private String fathername;

    @ManyToMany
    @JoinTable(
            name = "owner_pet",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;

    public Long getId() {return id;}

    public void setId(Long id) {this.id=id;}

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname=surname;}

    public String getFathername() {return fathername;}

    public void setFathername(String fathername) {this.fathername=fathername;}
}
