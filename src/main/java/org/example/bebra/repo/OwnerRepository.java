package org.example.bebra.repo;

import org.example.bebra.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findBySurname(String surname);
}
