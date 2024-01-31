package com.example.umbrella.mapper;

import com.example.umbrella.dto.UmbrellaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UmbrellaMapper {

    public List<UmbrellaDto> getUmbrellaList(String lockercode);

}
