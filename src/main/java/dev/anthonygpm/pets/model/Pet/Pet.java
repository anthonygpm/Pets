package dev.anthonygpm.pets.model.Pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private String race;

    private LocalDate birthDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private PetStatus petStatus;

    private String imageUrl;
}
