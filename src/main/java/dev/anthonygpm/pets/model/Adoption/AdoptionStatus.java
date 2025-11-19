package dev.anthonygpm.pets.model.Adoption;

import lombok.Getter;

@Getter
public enum AdoptionStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String status;

    AdoptionStatus(String status) {
        this.status = status;
    }
}
