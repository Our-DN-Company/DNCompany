package com.example.dncompany.mapper.help;
import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.dto.help.HelpSearchDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerDTO;
import com.example.dncompany.service.help.HelpService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
@Transactional
class HelpMapperTest {
    @Autowired
    HelpMapper helpMapper;
    @Autowired
    private HelpService helpService;

//    @Test
//    void searchHelpListTest() {
//        // 검색 조건 생성
//        HelpSearchDTO searchDTO = new HelpSearchDTO();
//        searchDTO.setSido("서울");
//        searchDTO.setGugun("강남");
//        searchDTO.setCareType("산책");
//
//        // 검색 실행
//        List<HelpListDTO> result = helpMapper.searchHelpList(searchDTO);
//
//        // 검색 결과
//        System.out.println("검색 결과 개수: " + result.size());
//        result.forEach(System.out::println);
//    }

}

