package dev.anthonygpm.pets.controller;

import dev.anthonygpm.pets.dto.AdoptionRequestDTO;
import dev.anthonygpm.pets.model.Adoption.Adoption;
import dev.anthonygpm.pets.service.AdoptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
@AllArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @GetMapping
    public List<Adoption> findAll() {
        return adoptionService.listAllAdoptions();
    }

    @GetMapping("/{id}")
    public Adoption findById(@PathVariable Long id) {
        return adoptionService.getAdoptionById(id);
    }

    @PostMapping
    public Adoption createAdoption(@RequestBody AdoptionRequestDTO dto) {
        return adoptionService.saveAdoption(dto);
    }

    @PutMapping("/{id}")
    public Adoption updateAdoption(@PathVariable Long id, @RequestBody AdoptionRequestDTO dto) {
        return adoptionService.updateAdoption(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAdoption(@PathVariable Long id) {
        adoptionService.deleteAdoption(id);
    }
}
