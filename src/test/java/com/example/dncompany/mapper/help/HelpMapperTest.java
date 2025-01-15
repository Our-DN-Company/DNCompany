package com.example.dncompany.mapper.help;
import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.dto.help.HelpSearchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HelpMapperTest {
    @Autowired
    HelpMapper helpMapper;

    @Test
    void searchHelpListTest() {
        // 검색 조건 생성
        HelpSearchDTO searchDTO = new HelpSearchDTO();
        searchDTO.setSido("서울");
        searchDTO.setGugun("강남");
        searchDTO.setCareType("산책");

        // 검색 실행
        List<HelpListDTO> result = helpMapper.searchHelpList(searchDTO);

        // 검색 결과
        System.out.println("검색 결과 개수: " + result.size());
        result.forEach(System.out::println);
    }
}

