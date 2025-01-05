package com.example.dncompany.dto.user.mypage;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class AddPetDTO {
   private Long petId;
   private String petSpecies;
   private String petName;
   private Long petAge;
   private String petGender;
   private LocalDate petBirthDate;
   private LocalDate adoptionDate;
   private Long usersId;


}
