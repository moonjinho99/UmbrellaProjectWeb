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
}
