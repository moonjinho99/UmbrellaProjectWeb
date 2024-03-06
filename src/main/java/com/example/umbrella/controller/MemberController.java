package com.example.umbrella.controller;

import com.example.umbrella.Service.MemberService;
import com.example.umbrella.dto.CenterDto;
import com.example.umbrella.dto.MemberDto;
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

import java.util.ArrayList;
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

        System.out.println("<<< 로그인(앱) - 사용자가 입력한 정보 >>>");
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

    // 사용자 정보(앱)
    @PostMapping(value = "/login-userInfo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<MemberDto> getUserInfo(@RequestBody MemberDto memberDto) {

        System.out.println("<<< 로그인(앱) - 사용자가 입력한 정보 >>>");
        System.out.println("아이디 : " + memberDto.getId());
        System.out.println("비밀번호 : " + memberDto.getPw());

        MemberDto mvo = memberService.memberLogin(memberDto);

        List<MemberDto> userInfo = new ArrayList<>();

        userInfo.add(mvo);
        System.out.println("mvo : " + mvo);

        return userInfo;
    }

    // 아이디 중복확인(앱)
    @PostMapping(value = "/id-check", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCheck(@RequestBody MemberDto memberDto) {

        System.out.println("<<< 아이디 중복확인(앱) - 사용자가 입력한 정보 >>>");
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

        System.out.println("<<< 회원가입(앱) - 사용자가 입력한 정보 >>>");
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

        String phoneReg = "(\\d{2,3})(\\d{3,4})(\\d{4})";
        String phone = "";  // 입력한 휴대폰 번호
        String afterPhone = ""; // 하이픈 추가한 휴대폰 번호

        phone = memberDto.getPhone();
        afterPhone = phone.replaceAll(phoneReg, "$1-$2-$3");
        System.out.println("phone : " + phone);
        System.out.println("afterPhone : " + afterPhone);

        memberDto.setPhone(afterPhone);

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
            member.setPhone("010-1004-1004");
            member.setLevel(2);

            memberService.createMember(member);
        }

        return "/member/login";
    }

    // 로그인
    @PostMapping(value = "login.do")
    public String login(HttpServletRequest request, MemberDto member, RedirectAttributes rttr) {
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
                session.setAttribute("loginInfo", mvo);

                rttr.addFlashAttribute("loginUser", mvo.getName());
                rttr.addFlashAttribute("level", mvo.getLevel());
                System.out.println("level : " + mvo.getLevel());

                page = "redirect:/admin-main";
            } else {
                rttr.addFlashAttribute("result", 0);
                System.out.println("result : " + rttr.addFlashAttribute("result", 0));

                page = "redirect:/umbrella-login";
            }
        } else {
            rttr.addFlashAttribute("result", 1);
            System.out.println("result : " + rttr.addFlashAttribute("result", 1));

            page = "redirect:/umbrella-login";
        }
        return page;
    }


    // 로그아웃
    @GetMapping(value = "logout.do")
    public String Logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/umbrella-login";
    }

    // 아이디 중복확인
    @PostMapping(value = "idCheck.do")
    @ResponseBody
    public String idCheckWeb(@RequestParam("id") String id) {
        System.out.println("<<< 아이디 중복확인 - 사용자가 입력한 정보 >>>");
        System.out.println("아이디 : " + id);

        int result = memberService.idCheck(id);
        System.out.println("result : " + result);

        if (result != 0) {
            return "fail";  // 아이디 중복
        } else {
            return "success"; // 아이디 중복 X
        }
    }

    // 정보생성 및 기관등록
    @PostMapping(value = "enrollMember.do")
    public String enrollMember(MemberDto member) {
        System.out.println("<<< 정보생성 및 기관등록 - 사용자가 입력한 정보 >>>");
        System.out.println("아이디 : " + member.getId());
        System.out.println("비밀번호 : " + member.getPw());
        System.out.println("이름 : " + member.getName());
        System.out.println("휴대폰번호 : " + member.getPhone());
        System.out.println("권한 : " + member.getLevel());

        String rawPw = ""; // 인코딩 전 비밀번호
        String encodePw = ""; // 인코딩 후 비밀번호

        rawPw = member.getPw(); // 입력한 비밀번호
        encodePw = passwordEncoding().encode(rawPw); // 비밀번호 인코딩
        System.out.println("rawPw : " + rawPw);
        System.out.println("encodePw : " + encodePw);

        member.setPw(encodePw);

        memberService.createMember(member);

        if (member.getLevel() == 1) {  // 기관인 경우
            CenterDto center = new CenterDto();

            center.setCentercode("");
            center.setCenterAddr("");
            center.setRegId(member.getId());

            memberService.createCenter(center);
        }

        return "redirect:/admin-main";
    }

    // 기관 등록 페이지(테스트용)
//    @GetMapping(value = "/enrollMember")
//    public String enroll() {
//
//        return "/enrollMember";
//    }

}
