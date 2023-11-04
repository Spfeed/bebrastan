package org.example.bebra.repo;

import org.example.bebra.sochniy.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PetRepository extends JpaRepository<Pet,Long> {
}
