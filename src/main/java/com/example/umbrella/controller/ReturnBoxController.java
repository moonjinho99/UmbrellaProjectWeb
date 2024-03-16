package com.example.umbrella.controller;

import com.example.umbrella.Service.CenterService;
import com.example.umbrella.Service.ReturnBoxService;
import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.dto.MemberDto;
import com.example.umbrella.dto.ReturnBoxDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReturnBoxController {
    
    @Autowired
    ReturnBoxService returnBoxService;
    @Autowired
    CenterService centerService;
    
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

    @PostMapping(value="/insert_returnBox")
    public String insertReturnBoxById(@RequestParam("returnBoxDetail_number")int returnBoxDetail_number, @RequestParam("returnBoxAddr") String returnBoxAddr, HttpServletRequest request){
        System.out.println("============================================insertReturnBoxById 진입");
        System.out.println(returnBoxDetail_number+returnBoxAddr);
        ReturnBoxDto returnBox = new ReturnBoxDto();

        //현재 로그인된 아이디로 centercode 가져옴
        HttpSession session = request.getSession();
        MemberDto loginUser = new MemberDto();
        loginUser = (MemberDto) session.getAttribute("loginInfo");
        log.debug(loginUser.toString());
        String id = loginUser.getId();
        String centercode = centerService.getCentercodeById(id);

        //returnBoxcode 생성
        int returnBox_count = returnBoxService.countReturnBox(centercode)+1;
        @Nullable String maxReturnBoxcode = returnBoxService.maxReturnBoxcode(centercode);
        String returnBoxcode = "";
        if(returnBox_count<=9){
            returnBoxcode = "return"+"0"+returnBox_count;
            if(returnBoxcode.equals(maxReturnBoxcode)){
                returnBox_count++;
                returnBoxcode = "return"+returnBox_count;
            }
        }else{
            returnBoxcode = "return"+returnBox_count;
            if(returnBoxcode.equals(maxReturnBoxcode)){
                returnBox_count++;
                returnBoxcode = "return"+returnBox_count;
            }
        }
        returnBox.setReturnBoxcode(returnBoxcode);


        //returnBox 등록
        returnBoxService.insertReturnBox(returnBoxcode,returnBoxAddr,centercode);

        for(int i=1;i<=returnBoxDetail_number;i++){
            String returnBoxDetailCode = returnBoxcode+"_";
            if(i<=9){
                String n = "0"+i;
                returnBoxDetailCode +=n;;
            }else{
                returnBoxDetailCode +=i;
            }

            log.debug(returnBoxDetailCode);
            returnBox.setReturnBoxDetailcode(returnBoxDetailCode);


            log.debug(returnBox.toString());
            returnBoxService.insertReturnBoxDetail(returnBox);

        }

        System.out.println("============================================insertReturnBoxById 완료");
        return "/center/center_returnbox_manage";
    }


    @PostMapping("return_umbrella")
    @ResponseBody
    public void returnBoxReg(@RequestBody Map<String,Object> returnUmbMap){
        System.out.println("받은 우산 데이터 : "+returnUmbMap.toString());
        returnBoxService.returnBoxReg(returnUmbMap);
    }
}
