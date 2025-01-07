package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MypageMapperTest {

    @Autowired
    MypageMapper mypageMapper;
    AddPetDTO addPetDTO;

    @BeforeEach
    void setUp(){
        addPetDTO = new AddPetDTO();
        addPetDTO.setPetId(6L);
        addPetDTO.setPetName("테스");
        addPetDTO.setPetSpecies("강아지");
        addPetDTO.setPetGender("F");
        addPetDTO.setPetBirthDate(LocalDate.of(1990, 1, 1));
        addPetDTO.setAdoptionDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    void addPet(){mypageMapper.addPet(addPetDTO);
    }

    @Test
    void petAgeCalculation() {
    }
}