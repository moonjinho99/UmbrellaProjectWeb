package com.example.umbrella.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping(value = "/admin-main")
    public String mainmenu(){
        return "admin/main";
    }



}
