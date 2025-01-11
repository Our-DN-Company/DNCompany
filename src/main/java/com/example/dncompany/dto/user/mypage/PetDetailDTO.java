package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @ToString
public class PetDetailDTO {
    private Long petId;
    private String petSpecies;
    private String petName;
    private String petGender;
    private LocalDate petBirthDate;
    private LocalDate adoptionDate;
}
