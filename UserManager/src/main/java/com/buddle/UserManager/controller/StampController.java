package com.buddle.UserManager.controller;


import com.buddle.UserManager.dto.StampRequestDto;
import com.buddle.UserManager.service.StampService;
import com.buddle.UserManager.dto.StampResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StampController {

    @Autowired
    StampService stampService;

    @PostMapping("stamp/check_condition")
    public Boolean checkAcquireStamp(@RequestBody StampRequestDto reqDto){return stampService.checkAcquireStamp(reqDto);}
    @PostMapping ("stamp/acquire_stamp")
    public Boolean acquireStamp(@RequestBody StampRequestDto reqDto) {return stampService.acquireStamp(reqDto);}

    @GetMapping("stamp/get_stamp")
    public StampResponseDto getStamp(@RequestParam Long user_number, @RequestParam Long stamp_id) {return stampService.getStamp(user_number, stamp_id);}
    //GET으로 하면 body 말고 html 쪽으로 나간다.

    @GetMapping("stamp/get_stamp_list")
    public List<StampResponseDto> getStampList(@RequestParam Long user_number){return stampService.getStampList(user_number);
    }


}



