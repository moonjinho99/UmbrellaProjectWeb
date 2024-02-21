package com.example.umbrella.Service;

import com.example.umbrella.dto.MemberDto;
import com.example.umbrella.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    @Autowired
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> getAllMemberList() {
        return memberMapper.getAllMemberList();
    }

    // 관리자 등록 여부 확인
    @Override
    public int firstAdmin(int level) {
        return memberMapper.firstAdmin(level);
    }

    // 정보 생성
    @Override
    public void createMember(MemberDto member) {
        memberMapper.createMember(member);
    }

    // 로그인
    @Override
    public MemberDto memberLogin(MemberDto member) {
        return memberMapper.memberLogin(member);
    }

    // 아이디 중복 확인
    @Override
    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }

    // 회원가입(앱)
    @Override
    public void joinUser(MemberDto member) {
        memberMapper.joinUser(member);
    }

    @Override
    public void deleteMember(String id){ memberMapper.deleteMember(id);}
}
