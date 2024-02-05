package com.example.umbrella.Service;

import com.example.umbrella.dto.CenterDto;
import com.example.umbrella.mapper.CenterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService{

    @Autowired
    CenterMapper centerMapper;

    public List<CenterDto> getAllCenter(){
        return centerMapper.getAllCenter();
    }
}
