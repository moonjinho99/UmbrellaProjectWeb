package com.example.umbrella.mapper;

import com.example.umbrella.dto.CenterDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CenterMapper {
    public List<CenterDto> getAllCenter();
}
