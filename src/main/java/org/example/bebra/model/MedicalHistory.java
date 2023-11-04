package org.example.bebra.model;
import jakarta.persistence.*;
import org.example.bebra.sochniy.Pet;

import java.util.Date;

@Entity
@Table(name="medical_histories")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Long getId() {return id;}

    public void setId(Long id) {this.id=id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date=date;}

    public String getType() {return type;}

    public void setType(String type) {this.type=type;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description=description;}

    public void setPet(Pet pet) { this.pet=pet;
    }

    public Pet getPet() { return pet;
    }
}
