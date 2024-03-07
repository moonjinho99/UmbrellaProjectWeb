package com.example.umbrella.Service;

import com.example.umbrella.dto.ReturnBoxDto;

import java.util.List;

public interface ReturnBoxService {

    public List<ReturnBoxDto> getAllReturnBox();
    public int countReturnBox(String centercode);
    public void deleteReturnBox(String centercode);
    public List<String> getAllReturnBoxCode(String centercode);
    public void deleteReturnBoxDetail(String lockercode);
}
