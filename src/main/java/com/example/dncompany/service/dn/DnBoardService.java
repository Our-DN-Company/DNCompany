package com.example.dncompany.service.dn;

import com.example.dncompany.dto.dn.*;
import com.example.dncompany.exception.dn.DnBoardNotFoundException;
import com.example.dncompany.mapper.dn.DnBoardMapper;
import com.example.dncompany.mapper.dn.DnProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DnBoardService {
    private final DnBoardMapper dnBoardMapper;
    private final DnProductMapper dnProductMapper;

    // 게시글 추가 기능
    public void addDnBoard (DnBoardWriteDTO dnBoardWriteDTO,
                            ProductDTO productDTO,
                            Long userId) {
        dnBoardWriteDTO.setUsersId(userId);
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        dnProductMapper.insertProduct(productDTO);

        DnSellBoardDTO dnSellBoardDTO = new DnSellBoardDTO();
        dnSellBoardDTO.setDnId(dnBoardWriteDTO.getDnId());
        dnSellBoardDTO.setProductId(productDTO.getProductId());
        dnSellBoardDTO.setUsersId(userId);
        dnBoardMapper.insertSellBoard(dnSellBoardDTO);
    }

    // 게시글 조회 기능
    public DnBoardDetailDTO getDnBoardById(Long dnId) {

        return dnBoardMapper.selectDnBoardById(dnId)
                .orElseThrow(() -> new DnBoardNotFoundException("게시글을 찾을 수 없음, ID : " + dnId));
    }
    
    // 게시글 전체 조회 기능
    public List<DnBoardListDTO> getDnBoardList(){

        return dnBoardMapper.selectAllDnBoardList();
    }

    // 게시글 수정 처리 기능
    public void modifyDnBoard(DnBoardModifyDTO boardModifyDTO,
                              ProductModifyDTO productModifyDTO) {
        dnBoardMapper.updateDnBoard(boardModifyDTO);
        dnProductMapper.updateProduct(productModifyDTO);
    }

}
