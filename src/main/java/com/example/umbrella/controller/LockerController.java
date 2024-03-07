package com.example.umbrella.controller;

import com.example.umbrella.Service.CenterService;
import com.example.umbrella.Service.LockerService;
import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.dto.MemberDto;
import com.example.umbrella.dto.UmbrellaDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LockerController {

    @Autowired
    LockerService lockerService;
    @Autowired
    CenterService centerService;

    //앱의 지도에 보관함 표시
    @GetMapping(value="/get_locker", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LockerDto> getLockerList()
    {
        System.out.println("지도 연결");
        return lockerService.getlocker();
    }


    @PostMapping(value="/insert_locker")
    public String insertLockerById(@RequestParam("lockerDetail_number")int lockerDetail_number, @RequestParam("lockerAddr") String lockerAddr, HttpServletRequest request){
        System.out.println("============================================insertLockerById 진입");
        System.out.println(lockerDetail_number+lockerAddr);
        LockerDto locker = new LockerDto();

        //현재 로그인된 아이디로 centercode 가져옴
        HttpSession session = request.getSession();
        MemberDto loginUser = new MemberDto();
        loginUser = (MemberDto) session.getAttribute("loginInfo");
        log.debug(loginUser.toString());
        String id = loginUser.getId();
        String centercode = centerService.getCentercodeById(id);

        //lockercode 생성
        int locker_count = lockerService.countLocker(centercode);
        String lockercode = decideLockercode(locker_count);
        locker.setLockercode(lockercode);


        //locker 등록
        lockerService.insertLocker(lockercode,centercode,lockerAddr);

        for(int i=1;i<=lockerDetail_number;i++){
            String lockerDetailCode = lockercode;
            if(i<=9){
                String n = "0"+i;
                lockerDetailCode +=n;;
            }else{
                lockerDetailCode +=i;
            }

            log.debug(lockerDetailCode);
            locker.setLockerDetailcode(lockerDetailCode);

            //4자리 비밀번호 생성
            Random random = new Random();
            int createNum = 0;
            String ranNum = "";
            StringBuilder resultNum = new StringBuilder();

            for(int j=0;j<4;j++){
                createNum = random.nextInt(9);
                ranNum = Integer.toString(createNum);
                resultNum.append(ranNum);
            }

            String lockerPw = resultNum.toString();

            log.debug(lockerPw);
            locker.setLockerPw(lockerPw);
            log.debug(locker.toString());

            lockerService.insertLockerDetail(locker);

        }

        System.out.println("============================================insertLockerById 완료");
        return "/center/center_storage_manage";
    }

    public String decideLockercode(int locker_count){
        System.out.println("============================================decideLockercode 진입");
        List<String> lockerCode_Alphabet = new ArrayList<>();
        lockerCode_Alphabet.add("A");
        lockerCode_Alphabet.add("B");
        lockerCode_Alphabet.add("C");
        lockerCode_Alphabet.add("D");
        lockerCode_Alphabet.add("E");
        lockerCode_Alphabet.add("F");
        lockerCode_Alphabet.add("G");
        lockerCode_Alphabet.add("H");
        lockerCode_Alphabet.add("I");
        lockerCode_Alphabet.add("J");
        lockerCode_Alphabet.add("K");
        lockerCode_Alphabet.add("L");
        lockerCode_Alphabet.add("M");
        lockerCode_Alphabet.add("N");
        lockerCode_Alphabet.add("O");
        lockerCode_Alphabet.add("P");
        lockerCode_Alphabet.add("Q");
        lockerCode_Alphabet.add("R");
        lockerCode_Alphabet.add("S");
        lockerCode_Alphabet.add("T");
        lockerCode_Alphabet.add("U");
        lockerCode_Alphabet.add("V");
        lockerCode_Alphabet.add("W");
        lockerCode_Alphabet.add("X");
        lockerCode_Alphabet.add("Y");
        lockerCode_Alphabet.add("Z");

        String lockercode = "lock" + lockerCode_Alphabet.get(locker_count);
        log.debug(lockercode);
        System.out.println("============================================decideLockercode 완료");
        return lockercode;
    }

    @ResponseBody
    @PostMapping(value = "getLockerList")
    public List<LockerDto> getAllLockerList(HttpServletRequest request){
        System.out.println("============================================getAllLockerList 진입");
        List<LockerDto> data = new ArrayList<>();
        //현재 로그인된 아이디로 centercode 가져옴
        HttpSession session = request.getSession();
        MemberDto loginUser = new MemberDto();
        loginUser = (MemberDto) session.getAttribute("loginInfo");
        log.debug(loginUser.toString());
        String id = loginUser.getId();
        String centercode = centerService.getCentercodeById(id);
        data = lockerService.getAllLockerByCentercode(centercode);
        System.out.println("============================================getAllLockerList 완료");
        return data;
    }

    @PostMapping(value="/delete_locker")
    public void deleteLockerByCentercode(String centercode){
        System.out.println("============================================deleteLockerByCentercode 진입");
        int locker_count = lockerService.countLocker(centercode);
        List<String> lockerCodes = new ArrayList<>();
        if(locker_count>0){
            lockerCodes = lockerService.getAllLockerCode(centercode);
            log.debug(lockerCodes.toString());
            for(String lockerCode : lockerCodes){
                lockerService.deleteLockerDetailByLocalcode(lockerCode);
            }
            lockerService.deleteLockerByCentercode(centercode);
        }
        System.out.println("============================================deleteLockerByCentercode 완료");
    }

    @PostMapping(value = "/delete_locker_by_lockercode")
    public String deleteLockerByLockercode(@RequestParam("lockercode") String lockercode){
        System.out.println("============================================deleteLockerByLockercode 진입");
        lockerService.deleteLockerDetailByLocalcode(lockercode);
        lockerService.deleteLockerByLockercode(lockercode);
        System.out.println("============================================deleteLockerByLockercode 완료");
        return "center/center_storage_manage";
    }

    @ResponseBody
    @PostMapping(value = "/update-locker-html")
    public LockerDto getLocker(@RequestParam("lockercode")String lockercode){
        System.out.println("============================================getLocker 진입");
        LockerDto locker = new LockerDto();
        locker = lockerService.getLocker(lockercode);
        log.debug(locker.toString());

        System.out.println("============================================getLocker 완료");
        return locker;
    }

    @PostMapping(value = "/update-locker-post")
    public String updateLockerPOST(@RequestParam("lockercode")String lockercode, @RequestParam("lockerAddr")String lockerAddr){
        System.out.println("============================================updateLockerPOST 진입");
        LockerDto locker = new LockerDto();
        locker.setLockercode(lockercode);
        locker.setLockerAddr(lockerAddr);
        lockerService.updateLockerAddr(locker);
        System.out.println("============================================updateLockerPOST 완료");
        return "center/center_storage_manage";
    }

    @GetMapping(value="/go_lockerDetail")
    public String goLockerDetailGET(@RequestParam("lockercode")String lockercode,Model model){
        System.out.println("============================================goLockerDetailGET 진입");
        model.addAttribute("lockercode", lockercode);
        System.out.println("============================================goLockerDetailGET 완료");

        return "center/center_lockerDetail_manage";
    }

    @ResponseBody
    @PostMapping(value = "/getLockerDetailList")
    public List<LockerDto> getAllLockerDetailList(@RequestParam("lockercode")String lockercode){
        System.out.println("============================================getAllLockerDetailList 진입");
        System.out.println("넘어온 lockercode="+lockercode);
        List<LockerDto> lockerDetail = new ArrayList<>();
        lockerDetail = lockerService.getAllLockerDetailByLockercode(lockercode);
        System.out.println("============================================getAllLockerDetailList 완료");
        return lockerDetail;
    }

    @PostMapping(value = "/delete_lockerDetail")
    public String deleteLockerDetail(@RequestParam("lockerDetailcode") String lockerDetailcode, @RequestParam("lockercode")String lockercode, Model model){
        System.out.println("============================================deleteLockerDetail 진입");
        lockerService.deleteLockerDetailByLockerDetailcode(lockerDetailcode);
        model.addAttribute("lockercode",lockercode);
        System.out.println("============================================deleteLockerDetail 완료");

        return "center/center_lockerDetail_manage";
    }

    @GetMapping(value="/go_umbrella")
    public String goUmbrellaGET(@RequestParam("umbrellacode")String umbrellacode,Model model){
        System.out.println("============================================goUmbrellaGET 진입");
        model.addAttribute("umbrellacode", umbrellacode);
        System.out.println("============================================goUmbrellaGET 완료");

        return "center/center_umbrella_manage";
    }

}
