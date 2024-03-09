package com.example.umbrella.mapper;

import com.example.umbrella.dto.UmbrellaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UmbrellaMapper {

    public List<UmbrellaDto> getUmbrellaList(String lockercode);

    //우산 대여
    public void rentalUmbrella(Map<String,Object> rentalUmbMap);

    //대여중인 우산 목록
    public List<UmbrellaDto> getMyRentalUmbList(Map<String,Object> myRentalUmbMap);

}
