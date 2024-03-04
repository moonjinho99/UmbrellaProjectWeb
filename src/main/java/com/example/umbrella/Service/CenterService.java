package com.example.umbrella.Service;

import com.example.umbrella.dto.CenterDto;

import java.util.List;

public interface CenterService {
    public List<CenterDto> getAllCenter();
    public CenterDto getCenter(String centercode);
    public void updateCenter(CenterDto center);
    public void updateCenterNotPw(CenterDto center);
    public String findRegId(String centercode);
    public void deleteCenter(String regId);
}
