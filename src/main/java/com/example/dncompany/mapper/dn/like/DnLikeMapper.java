package com.example.dncompany.mapper.dn.like;

import com.example.dncompany.dto.dn.like.DnLikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DnLikeMapper {
    void insertDnLike(DnLikeDTO dnLikeDTO);

    int countByUsersIdAndDnId(@Param("usersId") Long usersId, @Param("dnId") Long dnId);

    int countByDnId(Long DnId);

    void deleteByUsersIdAndDnId(@Param("usersId") Long usersId, @Param("dnId") Long dnId);
}
