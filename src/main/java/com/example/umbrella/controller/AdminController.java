package com.example.umbrella.controller;


import com.example.umbrella.Service.LockerService;
import com.example.umbrella.Service.MemberService;
import com.example.umbrella.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import com.example.umbrella.Service.CenterService;
import com.example.umbrella.dto.CenterDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @Autowired
    CenterService centerService;
    @Autowired
    LockerService lockerService;
    @Autowired
    MemberService memberService;

    public PasswordEncoder passwordEncoding() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping(value = "/admin-main")
    public String mainmenu(HttpSession session, MemberDto member, Model model) {
        member = (MemberDto) session.getAttribute("loginInfo");
        System.out.println("세션 정보 : " + member);

        model.addAttribute("loginUser", member.getName());
        model.addAttribute("level", member.getLevel());
        System.out.println("loginUser : " + member.getName());
        System.out.println("level : " + member.getLevel());

        return "/main";
    }

    @GetMapping(value = "/admin-main-menu")
    public String mainmenuActionAdmin(@RequestParam("buttonValue") String buttonValue, Model model) {
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

    //가연 datatables 연습 매핑
    @GetMapping("/adminCenterList")
    public String admin_center_manage_GET(){
        return "admin/admin_center_manage";
    }

    @GetMapping("/header")
    public String headerGET(){
        return "header";
    }

    @PostMapping("getCenterList")
    public @ResponseBody List<CenterDto> getCenterList(String id){
        List<CenterDto> data = centerService.getAllCenter();
        return data;
    }

    @PostMapping("update-center-html")
    public @ResponseBody CenterDto getCenter(String centercode){
        CenterDto center = centerService.getCenter(centercode);
        log.info(center.toString());
        return center;
    }

    @ResponseBody
    @PostMapping("update-center-post")
    public void updateCenter(@RequestParam("centercode") String centercode,@RequestParam("id") String id,@RequestParam("pw") String pw,@RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("centerAddr") String centerAddr){
        System.out.println(centercode+id+pw+name+phone+centerAddr);
        CenterDto center = new CenterDto();
        center.setCentercode(centercode);
        center.setId(id);
        String password = passwordEncoding().encode(pw);
        center.setPw(password);
        center.setName(name);
        center.setPhone(phone);
        center.setCenterAddr(centerAddr);
        centerService.updateCenter(center);
    }

    @PostMapping("/delete-center")
    public void deleteCenter(@RequestParam("centercode") String centercode){
        System.out.println("deleteCenter 진입=================================>");
        int locker_count = lockerService.countLocker(centercode);
        String regId="";
        if(locker_count==0){
            regId=centerService.findRegId(centercode);
        }else{
            lockerService.deleteLocker(centercode);
            regId=centerService.findRegId(centercode);
        }
        centerService.deleteCenter(regId);
        memberService.deleteMember(regId);
    }





}
