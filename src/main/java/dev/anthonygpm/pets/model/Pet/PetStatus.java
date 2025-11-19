package dev.anthonygpm.pets.model.Pet;

import lombok.Getter;

@Getter
public enum PetStatus {
    AVAILABLE("available"),
    ADOPTED("adopted");

    private final String role;

    PetStatus(String role) {
        this.role = role;
    }
}