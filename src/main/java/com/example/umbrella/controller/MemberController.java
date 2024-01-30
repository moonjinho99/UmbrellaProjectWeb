package com.example.umbrella.controller;

import com.example.umbrella.Service.MemberService;
import com.example.umbrella.dto.MemberDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login.do")
    public String loginGET(){
        List<MemberDto> memberDtoList = memberService.getAllMemberList();
        System.out.print("멤버 리스트 : "+memberDtoList);
        return "member/login";
    }

    // 로그인
    @PostMapping(value = "/login-user", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loginUser(@RequestBody MemberDto memberDto){

        System.out.println("넘어온 값 : "+ memberDto);

        if(memberDto.getId().equals("qwer") && memberDto.getPw().equals("1234"))
        {
            return "success";
        }

        else{
            return "fail";
        }
    }

    // 아이디 중복확인
    @PostMapping(value = "/id-check", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCheck(@RequestBody MemberDto memberDto){

        if(!(memberDto.getId().equals("qwer")))
        {
            return "success";
        }

        else{
            return "fail";
        }

    }

    // 회원가입
    @PostMapping(value = "/join-user", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String joinUser(@RequestBody MemberDto memberDto){

        if(memberDto.getId().equals("qqqq") && memberDto.getName().equals("방현식") && memberDto.getPw().equals("1234") && memberDto.getPhone().equals("01076565796"))
        {
            return "success";
        }

        else{
            return "fail";
        }

    }



}
