package dev.anthonygpm.pets.repository;

import dev.anthonygpm.pets.model.Pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findBySpeciesContainingIgnoreCase(String species);
    List<Pet> findByRaceContainingIgnoreCase(String race);
    List<Pet> findBySpeciesContainingIgnoreCaseAndRaceContainingIgnoreCase(String species, String race);
}

