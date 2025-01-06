package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class PetSlideDTO {
    Long petId;
    String petName;
    int petAge;
    String petGender;
    String petSpecies;
    LocalDate petBirthDate;
    LocalDate petAdoptionDate;
}
