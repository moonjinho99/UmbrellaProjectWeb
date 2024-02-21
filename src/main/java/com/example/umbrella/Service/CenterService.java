package com.example.umbrella.Service;

import com.example.umbrella.dto.CenterDto;

import java.util.List;

public interface CenterService {
    public List<CenterDto> getAllCenter();
    public CenterDto getCenter(String centercode);
    public void updateCenter(CenterDto center);
    public void deleteCenter(String id);
}
