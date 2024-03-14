package com.example.umbrella.mapper;

import com.example.umbrella.dto.ReturnBoxDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReturnBoxMapper {
    public List<ReturnBoxDto> getAllReturnBox();
    public int countReturnBox(String centercode);
    public void deleteReturnBox(String centercode);
    public List<String> getAllReturnBoxCode(String centercode);
    public void deleteReturnBoxDetail(String lockercode);
    public String maxReturnBoxcode(String centercode);
    public void insertReturnBox(String returnBoxcode, String returnBoxAddr, String centercode);
    public void insertReturnBoxDetail(ReturnBoxDto returnBox);
}
