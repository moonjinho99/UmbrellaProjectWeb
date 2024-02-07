package com.example.umbrella.Service;

import com.example.umbrella.dto.MemberDto;

import java.util.List;

public interface MemberService {
    public List<MemberDto> getAllMemberList();

    // 관리자 등록 여부 확인
    int firstAdmin(int level);

    // 정보 생성
    void createMember(MemberDto member);

    // 정보 조회
    MemberDto memberLogin(MemberDto member);
}
