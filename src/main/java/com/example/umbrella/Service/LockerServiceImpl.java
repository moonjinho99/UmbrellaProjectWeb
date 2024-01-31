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
}
