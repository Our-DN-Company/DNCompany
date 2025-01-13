package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.ProductDTO;
import com.example.dncompany.dto.dn.ProductModifyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DnProductMapper {
    // 게시글 추가
    void insertProduct(ProductDTO productDTO);

    // 게시글 수정
    void updateProduct(ProductModifyDTO productModifyDTO);
}
