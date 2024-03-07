package com.example.umbrella.mapper;

import com.example.umbrella.dto.LockerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LockerMapper {

    public List<LockerDto> getlocker();
    public int countLocker(String centercode);
    public void deleteLockerByCentercode(String centercode);
    public List<String> getAllLockerCode(String centercode);
    public void deleteLockerDetailByLocalcode(String lockercode);
    public void insertLocker(String lockercode, String centercode, String lockerAddr);
    public void insertLockerDetail(LockerDto locker);
    public List<LockerDto> getAllLockerByCentercode(String centercode);
    public void deleteLockerByLockercode(String lockercode);
    public LockerDto getLocker(String lockercode);
    public void updateLockerAddr(LockerDto locker);
    public List<LockerDto> getAllLockerDetailByLockercode(String lockercode);
    public void deleteLockerDetailByLockerDetailcode(String lockerDetailcode);
}
