package com.example.umbrella.controller;

import com.example.umbrella.Service.CenterService;
import com.example.umbrella.Service.LockerService;
import com.example.umbrella.Service.UmbrellaService;
import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.dto.MemberDto;
import com.example.umbrella.dto.UmbrellaDto;
import jakarta.annotation.Nullable;
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
    @Autowired
    UmbrellaService umbrellaService;

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
        int locker_count = lockerService.countLocker(centercode)+1;
        @Nullable String maxLockercode = lockerService.maxLockercode(centercode);
        String lockercode = "";
        if(locker_count<=9){
            lockercode = "lock"+"0"+locker_count;
            if(lockercode.equals(maxLockercode)){
                locker_count++;
                lockercode = "lock"+locker_count;
            }
        }else{
            lockercode = "lock"+locker_count;
            if(lockercode.equals(maxLockercode)){
                locker_count++;
                lockercode = "lock"+locker_count;
            }
        }
        locker.setLockercode(lockercode);


        //locker 등록
        lockerService.insertLocker(lockercode,centercode,lockerAddr);

        for(int i=1;i<=lockerDetail_number;i++){
            String lockerDetailCode = lockercode+"_";
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

    @GetMapping(value = "/go_locker")
    public String goLockerGET(){
        return "center/center_storage_manage";
    }

    @ResponseBody
    @PostMapping(value = "/update-lockerDetail-html")
    public LockerDto updateLockerDetailHTML(@RequestParam("lockerDetailcode")String lockerDetailcode){
        System.out.println("============================================updateLockerDetailHTML 진입");
        LockerDto lockerDetail = new LockerDto();
        lockerDetail = lockerService.getLockerDetailByLockerDetailcode(lockerDetailcode);
        log.debug(lockerDetail.toString());
        System.out.println("============================================updateLockerDetailHTML 완료");
        return lockerDetail;
    }


    @PostMapping(value = "/update-lockerDetail-post")
    public String updateLockerDetail(@RequestParam("lockercode")String lockercode, @RequestParam("lockerDetailcode")String lockerDetailcode,
                                     @RequestParam("lockerStatus")int lockerStatus, @RequestParam("umbrellacode") @Nullable Integer umbrellacode, @RequestParam("lockerPw")String lockerPw, Model model) throws NullPointerException{
        System.out.println("============================================updateLockerDetail 진입");

        LockerDto lockerDetail = new LockerDto();
        lockerDetail.setLockerStatus(lockerStatus);
        lockerDetail.setLockerDetailcode(lockerDetailcode);
        lockerDetail.setLockerPw(lockerPw);
        if(lockerStatus==0){
            umbrellacode = null;
            lockerDetail.setUmbrellacode(umbrellacode);
            lockerService.updateLockerDetailByLockerDetailcode(lockerDetail);
            model.addAttribute("lockercode",lockercode);

        }else if(lockerStatus==1){
            lockerDetail.setUmbrellacode(umbrellacode);
            lockerService.updateLockerDetailByLockerDetailcode(lockerDetail);
            model.addAttribute("lockercode",lockercode);

        }


        System.out.println("============================================updateLockerDetail 완료");

        return "center/center_lockerDetail_manage";
    }

    @ResponseBody
    @PostMapping("/countUmbrellacodeisUsedTo")
    public String countUmbrellacodeisUsedTo(int umbrellacode){
        System.out.println("============================================countUmbrellacodeisUsedTo 진입");
        int countUmbrellacodeisUsedTo;
        String result = "";
        countUmbrellacodeisUsedTo = lockerService.checkUmbrellacodeUsed(umbrellacode);
        int countUmbrella = umbrellaService.countUmbrella(umbrellacode);

        if(countUmbrellacodeisUsedTo>=1&&countUmbrella==1){
            result = "isUsedTo";
        }else if(countUmbrellacodeisUsedTo==0&&countUmbrella==1){
            result = "isNotUsedTo";
        }else if(countUmbrella==0){
            result = "noUmbrella";
        }
        log.debug(result);
        System.out.println("============================================countUmbrellacodeisUsedTo 완료");
        return result;
    }
}
