package com.example.umbrella.controller;

import com.example.umbrella.Service.MemberService;
import com.example.umbrella.dto.MemberDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login.do")
    public String loginGET(){
        List<MemberDto> memberDtoList = memberService.getAllMemberList();
        System.out.print(memberDtoList);
        return "member/login";
    }

}
