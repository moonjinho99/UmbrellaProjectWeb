package com.example.umbrella.Service;

import com.example.umbrella.dto.UmbrellaDto;

import java.util.List;
import java.util.Map;

public interface UmbrellaService {

    public List<UmbrellaDto> getUmbrellaList(String lockercode);

    public void rentalUmbrella(Map<String,Object> rentalUmbMap);

    public List<UmbrellaDto> getMyRentalUmbList(Map<String,Object> myRentalUmbMap);
}
