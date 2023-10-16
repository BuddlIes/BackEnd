package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Dto.UserLoginRequestDto;
import com.buddle.UserManager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/join")
    public String userJoin(@RequestBody UserJoinRequestDto reqDto)
    {
        return userService.join(reqDto);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody UserLoginRequestDto reqDto)
    {
        return userService.login(reqDto);
    }

}
