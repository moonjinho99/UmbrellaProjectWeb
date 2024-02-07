package com.example.umbrella.controller;

import com.example.umbrella.Service.CenterService;
import com.example.umbrella.dto.CenterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CenterController {

    @Autowired
    CenterService centerService;

    @GetMapping(value = "/center-main")
    public String mainmenu(){
        return "center/main";
    }

    @ResponseBody
    @PostMapping(value = "/getAllCenter")
    public List<CenterDto> getAllCenterToAjax(){
        List<CenterDto> centerList = new ArrayList<CenterDto>();
        centerList = centerService.getAllCenter();
        log.debug("centerList : "+centerList.toString());

        List<String> centerAddrList = new ArrayList<>();
        for(CenterDto center : centerList){
            centerAddrList.add(center.getCenterAddr());
        }
        log.debug("centerAddrList : "+centerAddrList.toString());

        return centerList;
    }

    @GetMapping(value = "/center-main-menu")
    public String mainmenuActionCenter(@RequestParam String buttonValue, Model model){
        System.out.println(buttonValue);
        String result = "";
        if(buttonValue.equals("우산 등록")){
            result="/center/center_umbrella_enroll";
        }else if(buttonValue.equals("우산 관리")){
            result="/center/center_umbrella_manage";
        }else if(buttonValue.equals("보관함 등록")){
            result="/center/center_storage_manage";
        }else if(buttonValue.equals("보관함 관리")){
            result="/center/center_storage_manage";
        }
        model.addAttribute("page",result);
        return result;
    }

}


