package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.StampDto;
import com.buddle.UserManager.Dto.StampRequestDto;
import com.buddle.UserManager.Dto.UserLoginRequestDto;
import com.buddle.UserManager.Service.StampService;
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
    public Integer acquireStamp(@RequestBody StampRequestDto reqDto) {return stampService.acquireStamp(reqDto);}

    @GetMapping("stamp/get_stamp")
    public StampDto getStamp(@RequestParam Long user_number, @RequestParam Long stamp_id) {return stampService.getStamp(user_number, stamp_id);}
    //GET으로 하면 body 말고 html 쪽으로 나간다.

    @GetMapping("stamp/get_stamp_list")
    public List<StampDto> getStampList(@RequestParam Long user_number){return stampService.getStampList(user_number);
    }


}
