package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Dto.MsgDto;
import com.buddle.UserManager.Dto.VolContentDto;
import com.buddle.UserManager.Dto.VolListDto;
import com.buddle.UserManager.Service.ChatService;
import com.buddle.UserManager.Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class VolController {

    @Autowired
    VolService volService;

    //봉사 게시물 리스트 보기
    @GetMapping("/volunteer/get_volunteer_list")
    public List<VolListDto> getVolunteerList(@RequestParam String hashtag)
    {
        return volService.checkVolList(hashtag);
    }

    //봉사 게시물 상세 내용 확인
    @GetMapping("/volunteer/get_volunteer_content")
    public VolContentDto getVolunteerContent(@RequestParam Long volunteerId)
    {
        return volService.checkVolContent(volunteerId);
    }

}
