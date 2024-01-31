package com.example.umbrella.controller;

import com.example.umbrella.Service.LockerService;
import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.dto.UmbrellaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LockerController {

    @Autowired
    LockerService lockerService;

    //앱의 지도에 보관함 표시
    @GetMapping(value="/get_locker", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LockerDto> getLockerList()
    {
        System.out.println("지도 연결");
        return lockerService.getlocker();
    }

}
