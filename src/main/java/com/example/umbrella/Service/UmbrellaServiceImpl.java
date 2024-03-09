package com.example.umbrella.Service;

import com.example.umbrella.dto.UmbrellaDto;
import com.example.umbrella.mapper.UmbrellaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UmbrellaServiceImpl implements UmbrellaService{

    @Autowired
    UmbrellaMapper umbrellaMapper;
    @Override
    public List<UmbrellaDto> getUmbrellaList(String lockercode) {
        return umbrellaMapper.getUmbrellaList(lockercode);
    }

    @Override
    public void rentalUmbrella(Map<String, Object> rentalUmbMap) {
        umbrellaMapper.rentalUmbrella(rentalUmbMap);
    }

    @Override
    public List<UmbrellaDto> getMyRentalUmbList(Map<String, Object> myRentalUmbMap) {
        return umbrellaMapper.getMyRentalUmbList(myRentalUmbMap);
    }
}
