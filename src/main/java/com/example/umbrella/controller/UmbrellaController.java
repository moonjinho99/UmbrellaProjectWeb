package com.example.umbrella.controller;



import com.example.umbrella.Service.UmbrellaService;
import com.example.umbrella.dto.UmbrellaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UmbrellaController {

    @Autowired
    UmbrellaService umbrellaService;

    @PostMapping(value = "/get_umbrella", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UmbrellaDto> getUmbrellaList(@RequestBody String lockercode){
        lockercode = lockercode.replaceAll("\"","");
        System.out.println("우산 : "+umbrellaService.getUmbrellaList(lockercode));
        return umbrellaService.getUmbrellaList(lockercode);
    }

}
