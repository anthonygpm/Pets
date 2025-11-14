package dev.anthonygpm.pets.model;

import lombok.Getter;

@Getter
public enum AdoptionStatus {
    AVAILABLE("available"),
    ADOPTED("adopted");

    private final String role;

    AdoptionStatus(String role) {
        this.role = role;
    }
}