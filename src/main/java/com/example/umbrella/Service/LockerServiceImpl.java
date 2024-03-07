package com.example.umbrella.Service;

import com.example.umbrella.dto.LockerDto;
import com.example.umbrella.mapper.LockerMapper;
import com.example.umbrella.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LockerServiceImpl implements LockerService{

    @Autowired
    private final LockerMapper lockerMapper;
    @Override
    public List<LockerDto> getlocker() {
        return lockerMapper.getlocker();
    }
    @Override
    public int countLocker(String centercode){return lockerMapper.countLocker(centercode);}
    @Override
    public void deleteLockerByCentercode(String centercode){lockerMapper.deleteLockerByCentercode(centercode);}
    @Override
    public List<String> getAllLockerCode(String centercode){return lockerMapper.getAllLockerCode(centercode);}
    @Override
    public void deleteLockerDetailByLocalcode(String lockercode){lockerMapper.deleteLockerDetailByLocalcode(lockercode);}
    @Override
    public void insertLocker(String lockercode, String centercode, String lockerAddr){lockerMapper.insertLocker(lockercode, centercode, lockerAddr);}
    @Override
    public void insertLockerDetail(LockerDto locker){lockerMapper.insertLockerDetail(locker);}
    @Override
    public List<LockerDto> getAllLockerByCentercode(String centercode){return lockerMapper.getAllLockerByCentercode(centercode);}
    @Override
    public void deleteLockerByLockercode(String lockercode){lockerMapper.deleteLockerByLockercode(lockercode);}
    @Override
    public LockerDto getLocker(String lockercode){return lockerMapper.getLocker(lockercode);}
    @Override
    public void updateLockerAddr(LockerDto locker){lockerMapper.updateLockerAddr(locker);}
    @Override
    public List<LockerDto> getAllLockerDetailByLockercode(String lockercode){return lockerMapper.getAllLockerDetailByLockercode(lockercode);}
    @Override
    public void deleteLockerDetailByLockerDetailcode(String lockerDetailcode){lockerMapper.deleteLockerDetailByLockerDetailcode(lockerDetailcode);}

}
