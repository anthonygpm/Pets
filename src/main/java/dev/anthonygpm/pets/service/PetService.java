package dev.anthonygpm.pets.service;

import dev.anthonygpm.pets.model.Pet;
import dev.anthonygpm.pets.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public List<Pet> listAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    public List<Pet> searchPets(String species, String race) {
        if ((species == null || species.isBlank()) && (race == null || race.isBlank())) {
            return listAllPets();
        }

        if (species != null && !species.isBlank() && (race == null || race.isBlank())) {
            return petRepository.findBySpeciesContainingIgnoreCase(species);
        }

        if ((species == null || species.isBlank()) && race != null && !race.isBlank()) {
            return petRepository.findByRaceContainingIgnoreCase(race);
        }

        return petRepository.findBySpeciesContainingIgnoreCaseAndRaceContainingIgnoreCase(species, race);
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet updatedPet) {
        Pet existingPet = getPetById(id);

        existingPet.setName(updatedPet.getName());
        existingPet.setSpecies(updatedPet.getSpecies());
        existingPet.setRace(updatedPet.getRace());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setDescription(updatedPet.getDescription());
        existingPet.setAdoptionStatus(updatedPet.getAdoptionStatus());

        return petRepository.save(existingPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
