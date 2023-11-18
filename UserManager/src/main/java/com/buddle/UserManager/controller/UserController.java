package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.LoginRequestResponseDto;
import com.buddle.UserManager.dto.ResponseDataDto;
import com.buddle.UserManager.dto.UserJoinRequestDto;
import com.buddle.UserManager.dto.UserLoginRequestDto;
import com.buddle.UserManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDataDto userLogin(@RequestBody UserLoginRequestDto reqDto)
    {
        return userService.login(reqDto);
    }

}
