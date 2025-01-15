package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @ToString
public class PetListDTO {
    private Long petId;
    private String petSpecies;
    private String petName;
    private int petAge;
    private String petGender;
    private LocalDate petBirthDate;
    private LocalDate adoptionDate;
    private Long userId;

    private Long petImgId;
    private String petOriginalImageName;
    private String petUuid;
    private String petImagePath;
    private String petExtension;
    private LocalDateTime petRegDate;
}
