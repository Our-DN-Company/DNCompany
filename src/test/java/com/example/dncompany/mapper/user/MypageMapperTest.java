package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.mypage.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MypageMapperTest {

    private static final Logger log = LoggerFactory.getLogger(MypageMapperTest.class);
    @Autowired
    MypageMapper mypageMapper;
    AddPetDTO addPetDTO;
    HelpYouListDTO helpYouListDTO;

    @BeforeEach
    void setUp() {
        addPetDTO = new AddPetDTO();
//        addPetDTO.setPetId(6L);
        addPetDTO.setPetName("테스트");
        addPetDTO.setPetSpecies("강아지");
        addPetDTO.setPetGender("F");
        addPetDTO.setUsersId(6L);

        addPetDTO.setPetBirthDate(LocalDate.of(1990, 1, 1));
        addPetDTO.setAdoptionDate(LocalDate.of(1990, 1, 1));


    }

    @Test
    void insertPet() {
        mypageMapper.insertPet(addPetDTO);

        List<PetListDTO> petList = mypageMapper.selectPetList(6L);

        assertThat(petList)
                .isNotEmpty()
                .extracting("petName")
                .contains("테스트");
    }


    @Test
    void userProfile() {
        Optional<UserProfileDTO> userProfile = mypageMapper.userProfile(21L);


    }

    @Test
    void helpMeListById() {


    }

    @Test
    void mypageMainList() {

        List<HelpMeListDTO> helpMeList = mypageMapper.MyPageMainHelpMeListById(81L);

        assertThat(helpMeList)
                .isNotEmpty();
        log.debug(helpMeList.toString());
    }


    @Test
    void updateHelpStatus() {
        mypageMapper.updateHelpStatus(142L,161L);


    }
}







