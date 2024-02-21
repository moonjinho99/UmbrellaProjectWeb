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
    public CenterDto getCenter(String centercode) { return centerMapper.getCenter(centercode);}
    public void updateCenter(CenterDto center){ centerMapper.updateCenter(center); centerMapper.updateMember(center);}
    public String findRegId(String centercode){return centerMapper.findRegId(centercode);}
    public void deleteCenter(String regId){centerMapper.deleteCenter(regId); }

}
