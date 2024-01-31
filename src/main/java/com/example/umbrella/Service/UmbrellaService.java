package com.example.umbrella.Service;

import com.example.umbrella.dto.UmbrellaDto;

import java.util.List;

public interface UmbrellaService {

    public List<UmbrellaDto> getUmbrellaList(String lockercode);
}
