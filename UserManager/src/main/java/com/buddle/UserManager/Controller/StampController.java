package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ReviewRequestDto;
import com.buddle.UserManager.Dto.StampDto;
import com.buddle.UserManager.Service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StampController {

    @Autowired
    StampService stampService;

    @PostMapping("/get_stamp")
    public String getStamp(@RequestBody StampDto stampDto) {return stampService.getStamp(stampDto);}
    @PostMapping("/get_stamp_list")
    public String getStampList(@RequestBody StampDto stampDto)
    {
        return stampService.getStampList(stampDto);
    }

    /*json으로 리턴하려면 어떻게 해야 할까 -> 결국엔 String 형태로 리턴하면 된당 안에서 json으로 이미 만든듯*/

}
