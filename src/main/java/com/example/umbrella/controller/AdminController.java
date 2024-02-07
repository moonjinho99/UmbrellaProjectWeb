package com.example.umbrella.controller;

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
    public String mainmenu(){
        return "admin/main";
    }

    @GetMapping(value = "/admin-main-menu")
    public String mainmenuAction(@RequestParam String buttonValue, Model model){
        log.info(buttonValue);
        String result = "";
        if(buttonValue.equals("기관 등록")){
            result="/admin/admin_center_enroll";
        }else if(buttonValue.equals("기관 관리")){
            result="/admin/admin_center_manage";
        }
        model.addAttribute("page",result);
        return result;
    }


}
