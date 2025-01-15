package com.example.dncompany.dto.help.pet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class HelpPetListDTO {
    private Long petId;
    private String petSpecies;
    private String petName;
    private Integer petAge;
    private String petGender;
    private LocalDate petBirthDate;
    private LocalDate adoptionDate;
    private Long usersId;
}
