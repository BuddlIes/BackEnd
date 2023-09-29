package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Service.ChatService;
import com.buddle.UserManager.Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class VolController {

    @Autowired
    VolService volService;

//    @GetMapping("/vol/get_vol_list")
//    public List<ChatRoomDto> getChatRoomList(@RequestParam Long memberId)
//    {
////        return ;
//    }

}
