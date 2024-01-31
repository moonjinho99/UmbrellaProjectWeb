package com.example.umbrella.mapper;

import com.example.umbrella.dto.LockerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LockerMapper {

    public List<LockerDto> getlocker();
}
