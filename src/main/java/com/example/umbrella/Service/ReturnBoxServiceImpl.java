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
}
