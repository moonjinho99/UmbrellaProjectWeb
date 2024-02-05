package com.example.umbrella.controller;

import com.example.umbrella.Service.CenterService;
import com.example.umbrella.dto.CenterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CenterController {

    @Autowired
    CenterService centerService;

    @GetMapping(value = "/umbrella-main")
    public String mainmenu(){
        return "main";
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

}


