package dev.anthonygpm.pets.repository;

import dev.anthonygpm.pets.model.Adoption.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
