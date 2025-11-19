package dev.anthonygpm.pets.service;

import dev.anthonygpm.pets.dto.AdoptionRequestDTO;
import dev.anthonygpm.pets.model.Adoption.Adoption;
import dev.anthonygpm.pets.model.Pet.Pet;
import dev.anthonygpm.pets.model.Users;
import dev.anthonygpm.pets.repository.AdoptionRepository;
import dev.anthonygpm.pets.repository.PetRepository;
import dev.anthonygpm.pets.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;
    private final UsersRepository usersRepository;

    public List<Adoption> listAllAdoptions() {
        return adoptionRepository.findAll();
    }

    public Adoption getAdoptionById(Long id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption not found with id: " + id));
    }

    public Adoption saveAdoption(AdoptionRequestDTO dto) {
        Pet pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.petId()));

        Users user = usersRepository.findById(dto.usersId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.usersId()));

        Adoption adoption = new Adoption();
        adoption.setPet(pet);
        adoption.setUsers(user);
        adoption.setAdoptionStatus(dto.adoptionStatus());

        return adoptionRepository.save(adoption);
    }

    public Adoption updateAdoption(Long id, AdoptionRequestDTO dto) {
        Adoption existingAdoption = getAdoptionById(id);

        if (dto.petId() != null) {
            Pet pet = petRepository.findById(dto.petId())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.petId()));
            existingAdoption.setPet(pet);
        }

        if (dto.usersId() != null) {
            Users user = usersRepository.findById(dto.usersId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.usersId()));
            existingAdoption.setUsers(user);
        }

        if (dto.adoptionStatus() != null) {
            existingAdoption.setAdoptionStatus(dto.adoptionStatus());
        }

        return adoptionRepository.save(existingAdoption);
    }

    public void deleteAdoption(Long id) {
        adoptionRepository.deleteById(id);
    }
}
