package dev.anthonygpm.pets.controller;

import dev.anthonygpm.pets.model.Pet.Pet;
import dev.anthonygpm.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public List<Pet> listAllPets(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String race
    ) {
        if ((species != null && !species.isBlank()) || (race != null && !race.isBlank())) {
            return petService.searchPets(species, race);
        }
        return petService.listAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.savePet(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadPetImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file
    ) {
        try {
            Pet updatedPet = petService.uploadPetImage(id, file);
            return ResponseEntity.ok(updatedPet);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload error: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}
