package dev.anthonygpm.pets.service;

import dev.anthonygpm.pets.model.Pet.Pet;
import dev.anthonygpm.pets.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final CloudinaryService cloudinaryService;

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
        existingPet.setPetStatus(updatedPet.getPetStatus());

        return petRepository.save(existingPet);
    }

    public Pet uploadPetImage(Long petId, MultipartFile file) throws IOException {
        Pet pet = getPetById(petId);

        if (pet.getImageUrl() != null && !pet.getImageUrl().isEmpty()) {
            String publicId = cloudinaryService.extractPublicId(pet.getImageUrl());
            if (publicId != null) {
                try {
                    cloudinaryService.deleteImage(publicId);
                } catch (Exception e) {
                    System.err.println("Last image couldn't be deleted: " + e.getMessage());
                }
            }
        }

        String imageUrl = cloudinaryService.uploadImage(file);
        pet.setImageUrl(imageUrl);

        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        Pet pet = getPetById(id);

        if (pet.getImageUrl() != null && !pet.getImageUrl().isEmpty()) {
            String publicId = cloudinaryService.extractPublicId(pet.getImageUrl());
            if (publicId != null) {
                try {
                    cloudinaryService.deleteImage(publicId);
                } catch (IOException e) {
                    System.err.println("The image couldn't be deleted: " + e.getMessage());
                }
            }
        }

        petRepository.deleteById(id);
    }
}
