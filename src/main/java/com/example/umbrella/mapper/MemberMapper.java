package com.example.umbrella.mapper;

import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    public List<MemberDto> getAllMemberList();

    // 관리자 등록 여부 확인
    int firstAdmin(int level);

    // 정보 조회
    MemberDto memberLogin(MemberDto member);

    // 정보 생성
    void createMember(MemberDto member);

}
