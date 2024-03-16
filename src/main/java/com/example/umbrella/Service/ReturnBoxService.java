package com.example.umbrella.Service;

import com.example.umbrella.dto.ReturnBoxDto;

import java.util.List;
import java.util.Map;

public interface ReturnBoxService {

    public List<ReturnBoxDto> getAllReturnBox();
    public int countReturnBox(String centercode);
    public void deleteReturnBox(String centercode);
    public List<String> getAllReturnBoxCode(String centercode);
    public void deleteReturnBoxDetail(String lockercode);
    public String maxReturnBoxcode(String centercode);
    public void insertReturnBox(String returnBoxcode, String returnBoxAddr, String centercode);
    public void insertReturnBoxDetail(ReturnBoxDto returnBox);

    public void returnBoxReg(Map<String,Object> returnUmbMap);

}
