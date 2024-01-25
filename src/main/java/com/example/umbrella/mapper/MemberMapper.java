package com.example.umbrella.mapper;

import com.example.umbrella.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    public List<MemberDto> getAllMemberList();
}
