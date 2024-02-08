package com.example.umbrella.controller;

import com.example.umbrella.Service.MemberService;
import com.example.umbrella.dto.MemberDto;
import com.example.umbrella.dto.UmbrellaDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    public PasswordEncoder passwordEncoding() {
        return new BCryptPasswordEncoder();
    }

    // 로그인(앱)
    @PostMapping(value = "/login-user", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loginUser(@RequestBody MemberDto memberDto) {

        System.out.println("<<<로그인(앱) 사용자가 입력한 정보>>>");
        System.out.println("아이디 : " + memberDto.getId());
        System.out.println("비밀번호 : " + memberDto.getPw());

        MemberDto mvo = memberService.memberLogin(memberDto);  // 입력한 아이디와 일치하는 DB상의 아이디가 있는지 확인

        String rawPw = "";
        String encodePw = "";

        if (mvo != null) {  // 아이디 일치
            rawPw = memberDto.getPw(); // 입력한 비밀번호
            encodePw = mvo.getPw(); // DB에 저장된 비밀번호(암호화)
            System.out.println("rawPw : " + rawPw);
            System.out.println("encodePw : " + encodePw);

            if (passwordEncoding().matches(rawPw, encodePw)) { // 비밀번호 일치
                mvo.setPw("");
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }

    // 아이디 중복확인(앱)
    @PostMapping(value = "/id-check", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCheck(@RequestBody MemberDto memberDto) {

        System.out.println("<<<아이디 중복확인(앱) 사용자가 입력한 정보>>>");
        System.out.println("아이디 : " + memberDto.getId());

        int result = memberService.idCheck(memberDto.getId());
        System.out.println("result : " + result);

        if (result == 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 회원가입(앱)
    @PostMapping(value = "/join-user", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String joinUser(@RequestBody MemberDto memberDto) {

        System.out.println("<<<회원가입(앱) 사용자가 입력한 정보>>>");
        System.out.println("아이디 : " + memberDto.getId());
        System.out.println("비밀번호 : " + memberDto.getPw());
        System.out.println("이름 : " + memberDto.getName());
        System.out.println("휴대폰번호 : " + memberDto.getPhone());

        String rawPw = ""; // 인코딩 전 비밀번호
        String encodePw = ""; // 인코딩 후 비밀번호

        rawPw = memberDto.getPw(); // 입력한 비밀번호
        encodePw = passwordEncoding().encode(rawPw); // 비밀번호 인코딩
        System.out.println("rawPw : " + rawPw);
        System.out.println("encodePw : " + encodePw);

        memberDto.setPw(encodePw);

        memberService.joinUser(memberDto);

        if (!memberDto.getId().isEmpty() && !memberDto.getName().isEmpty() &&
                !memberDto.getPw().isEmpty() && !memberDto.getPhone().isEmpty()) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 로그인 페이지
    @GetMapping(value = "/umbrella-login")
    public String loginView() {
        MemberDto member = new MemberDto();
        // 관리자 등록 여부 확인
        int result = memberService.firstAdmin(member.getLevel());
        System.out.println("result : " + result);

        // 기존에 관리자가 존재하지 않는 경우 최초 관리자 정보 생성
        if (result == 0) {
            member.setId("admin");
            member.setPw(passwordEncoding().encode(("1234")));
            member.setName("관리자");
            member.setPhone("01010041004");
            member.setLevel(2);

            memberService.createMember(member);
        }

        return "member/login";
    }

    // 로그인
    @PostMapping(value = "login.do")
    public String Login(HttpServletRequest request, MemberDto member, RedirectAttributes rttr) {
        HttpSession session = request.getSession();
        String page = "";
        String rawPw = "";
        String encodePw = "";

        MemberDto mvo = memberService.memberLogin(member);  // 입력한 아이디와 일치하는 DB상의 아이디가 있는지 확인

        if (mvo != null) {  // 아이디 일치
            rawPw = member.getPw(); // 입력한 비밀번호
            encodePw = mvo.getPw(); // DB에 저장된 비밀번호(암호화)
            System.out.println("rawPw : " + rawPw);
            System.out.println("encodePw : " + encodePw);

            if (passwordEncoding().matches(rawPw, encodePw)) { // 비밀번호 일치
                mvo.setPw("");
                if (mvo.getLevel() == 1) {   // 기관인 경우
                    session.setAttribute("organ", mvo);
                    System.out.println("level : " + mvo.getLevel());
                    page = "redirect:/center-main";
                } else if (mvo.getLevel() == 2) {    // 관리자인 경우
                    session.setAttribute("admin", mvo);
                    System.out.println("level : " + mvo.getLevel());
                    page = "redirect:/admin-main";
                }
            } else {
                rttr.addFlashAttribute("result", 0);
                System.out.println("result : " + rttr.addFlashAttribute("result", 0));
//                rttr.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
                return "redirect:/umbrella-login";
            }
        } else {
            rttr.addFlashAttribute("result", 1);
            System.out.println("result : " + rttr.addFlashAttribute("result", 1));
//            rttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            page = "redirect:/umbrella-login";
        }
        return page;
    }


    // 로그아웃
    @GetMapping(value = "logout.do")
    public String Logout(HttpServletRequest request) {
        log.info("로그아웃");

        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/umbrella-login";
    }
}
