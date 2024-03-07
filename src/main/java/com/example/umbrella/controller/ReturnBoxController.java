package com.example.umbrella.controller;

import com.example.umbrella.Service.ReturnBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReturnBoxController {
    
    @Autowired
    ReturnBoxService returnBoxService;
    
    @PostMapping(value="/delete_returnBox")
    public void deleteLockerByCentercode(String centercode){
        int locker_count = returnBoxService.countReturnBox(centercode);
        List<String> lockerCodes = new ArrayList<>();
        if(locker_count>0){
            lockerCodes = returnBoxService.getAllReturnBoxCode(centercode);
            log.debug(lockerCodes.toString());
            for(String lockerCode : lockerCodes){
                returnBoxService.deleteReturnBoxDetail(lockerCode);
            }
            returnBoxService.deleteReturnBox(centercode);
        }

    }
}
