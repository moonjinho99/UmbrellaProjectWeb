package com.example.umbrella.mapper;

import com.example.umbrella.dto.ReturnBoxDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReturnBoxMapper {
    public List<ReturnBoxDto> getAllReturnBox();
}
