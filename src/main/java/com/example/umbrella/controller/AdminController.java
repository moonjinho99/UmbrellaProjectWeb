package com.example.umbrella.controller;

import com.example.umbrella.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping(value = "/admin-main")
    public String mainmenu(HttpSession session, MemberDto member, Model model) {
        member = (MemberDto) session.getAttribute("loginInfo");
        System.out.println("세션 정보 : " + member);

        model.addAttribute("loginUser", member.getName());
        model.addAttribute("level", member.getLevel());
        System.out.println("loginUser : " + member.getName());
        System.out.println("level : " + member.getLevel());

//        if (session.getMaxInactiveInterval() == 0) {
//            return "/logout.do";
//        }

        return "admin/main";
    }

    @GetMapping(value = "/admin-main-menu")
    public String mainmenuActionAdmin(@RequestParam String buttonValue, Model model) {
        System.out.println(buttonValue);
        String result = "";
        if (buttonValue.equals("기관 등록")) {
            result = "/admin/admin_center_enroll";
        } else if (buttonValue.equals("기관 관리")) {
            result = "/admin/admin_center_manage";
        }
        model.addAttribute("page", result);
        return result;
    }


}
