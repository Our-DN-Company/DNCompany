package com.example.dncompany.service.help.pet;

import com.example.dncompany.dto.help.pet.HelpPetListDTO;
import com.example.dncompany.mapper.help.pet.HelpPetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HelpPetService {
    private final HelpPetMapper helpPetMapper;

    public List<HelpPetListDTO> getPetListByUsersId(Long usersId){
        return helpPetMapper.selectByUsersId(usersId);
    }
}
