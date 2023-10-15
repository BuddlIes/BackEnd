package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.*;
import com.buddle.UserManager.Service.CommentService;
import com.buddle.UserManager.Service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class VolController {

    @Autowired
    VolService volService;

    @Autowired
    CommentService commentService;

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

    @PostMapping("/volunteer/register_vol")
    public String getRegisterVolunteer(@RequestBody VolUploadRequestDto reqDto)
    {
        return volService.registerVol(reqDto);
    }

    @GetMapping("/volunteer/get_comment_list")
    public List<VolCommentDto> getCommentList(@RequestParam Long volunteerId)
    {
        return commentService.checkCommentsList(volunteerId);
    }

    @PostMapping("/volunteer/write_comment")
    public String getWriteComment(@RequestBody VolCommentDto reqDto)
    {
        return commentService.writeComment(reqDto);
    }

    @GetMapping("/volunteer/get_my_completed_volList")
    public List<VolListDto> getMyCompletedVolList(@RequestParam Long whoVol)
    {
        return volService.checkMyCompletedVolList(whoVol);
    }

}
