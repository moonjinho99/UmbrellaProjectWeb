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
import java.util.Map;

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


    @PostMapping(value="/rental_umbrella", produces = "application/json;charset=utf-8")
    @ResponseBody
    public void rentalUmbrella(@RequestBody Map<String,Object> rentalUmbMap)
    {
        System.out.println("우산 코드 : "+rentalUmbMap.get("umbrella_code"));
        System.out.println("대여 계정 : "+rentalUmbMap.get("rentalId"));
        System.out.println("대여 상태 : "+rentalUmbMap.get("rentalStatus"));
        System.out.println("대여 날짜 : "+rentalUmbMap.get("rentalTime"));
        System.out.println("반납 날짜 : "+rentalUmbMap.get("returnTime"));

        umbrellaService.rentalUmbrella(rentalUmbMap);
    }

    @PostMapping(value = "/my_rental_umbrella", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UmbrellaDto> getMyRentalUmbList(@RequestBody Map<String,Object> myInfo)
    {
        System.out.println("접속중인 계정 : "+myInfo.get("rentalId"));
        System.out.println("대여 상태 : "+myInfo.get("rentalStatus"));

        return umbrellaService.getMyRentalUmbList(myInfo);
    }


}
