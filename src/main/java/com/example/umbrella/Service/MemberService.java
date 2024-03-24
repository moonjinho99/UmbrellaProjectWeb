package com.example.umbrella.Service;

import com.example.umbrella.dto.CenterDto;
import com.example.umbrella.dto.MemberDto;

import java.util.List;

public interface MemberService {
    public List<MemberDto> getAllMemberList();

    // 관리자 등록 여부 확인
    int firstAdmin(int level);

    // 정보 생성
    void createMember(MemberDto member);

    // 로그인
    MemberDto memberLogin(MemberDto member);

    // 아이디 중복확인
    int idCheck(String id);

    // 회원가입(앱)
    void joinUser(MemberDto member);

    // 비밀번호 변경(앱)
    int pwUpdate(MemberDto member);

    // center 테이블에 정보 등록
    void createCenter(CenterDto center);

    void deleteMember(String id);
}
