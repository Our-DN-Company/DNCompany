package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;
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

    @Test
    void addPet() {

        addPetDTO = new AddPetDTO();
        addPetDTO.setPetId(1L);
        addPetDTO.setPetName("테스트");
        addPetDTO.setPetSpecies("강아지");
        addPetDTO.setPetGender("M");
        addPetDTO.setPetBirthDate(LocalDate.of(1990, 1, 1));
        addPetDTO.setAdoptionDate(LocalDate.of(1990, 1, 1));
        addPetDTO.setUsersId(6L);
        mypageMapper.addPet(addPetDTO);
    }
}