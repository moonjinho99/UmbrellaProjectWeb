package com.example.umbrella.Service;

import com.example.umbrella.dto.ReturnBoxDto;
import com.example.umbrella.mapper.ReturnBoxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnBoxServiceImpl implements ReturnBoxService{
    @Autowired
    ReturnBoxMapper returnBoxMapper;

    @Override
    public List<ReturnBoxDto> getAllReturnBox(){
       return returnBoxMapper.getAllReturnBox();
    }
    @Override
    public int countReturnBox(String centercode){return returnBoxMapper.countReturnBox(centercode);}
    @Override
    public void deleteReturnBox(String centercode){returnBoxMapper.deleteReturnBox(centercode);}
    @Override
    public List<String> getAllReturnBoxCode(String centercode){return returnBoxMapper.getAllReturnBoxCode(centercode);}
    @Override
    public void deleteReturnBoxDetail(String lockercode){returnBoxMapper.deleteReturnBoxDetail(lockercode);}
    @Override
    public String maxReturnBoxcode(String centercode){return returnBoxMapper.maxReturnBoxcode(centercode);}
    @Override
    public void insertReturnBox(String returnBoxcode, String returnBoxAddr, String centercode){returnBoxMapper.insertReturnBox(returnBoxcode,returnBoxAddr,centercode);}
    @Override
    public void insertReturnBoxDetail(ReturnBoxDto returnBox){returnBoxMapper.insertReturnBoxDetail(returnBox);}

}
