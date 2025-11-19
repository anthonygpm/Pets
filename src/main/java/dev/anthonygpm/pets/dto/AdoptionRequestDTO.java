package dev.anthonygpm.pets.dto;

import dev.anthonygpm.pets.model.Adoption.AdoptionStatus;

public record AdoptionRequestDTO(
        Long petId,
        Long usersId,
        AdoptionStatus adoptionStatus
) {
}
