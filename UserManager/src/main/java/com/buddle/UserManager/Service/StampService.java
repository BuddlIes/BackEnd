package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.StampDto;
import com.buddle.UserManager.Repository.StampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StampService {
    
    @Autowired
    StampRepository stampRepository;


    public String getStamp(StampDto stampDto) {
    }

    public String getStampList(StampDto stampDto) {
    }
}
