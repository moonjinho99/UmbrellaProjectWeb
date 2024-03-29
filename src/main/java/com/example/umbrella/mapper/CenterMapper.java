package com.example.umbrella.mapper;

import com.example.umbrella.dto.CenterDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CenterMapper {
    public List<CenterDto> getAllCenter();
    public CenterDto getCenter(String centercode);
    public void updateCenter(CenterDto center);
    public void updateCenterNotPw(CenterDto center);
    public void updateMember(CenterDto center);
    public String findRegId(String centercode);
    public void deleteCenter(String regId);
    public String getCentercodeById(String id);
}
