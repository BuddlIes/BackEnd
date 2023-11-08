package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.service.CommentService;
import com.buddle.UserManager.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseDataDto getRegisterVolunteer(@RequestBody VolUploadRequestDto reqDto)
    {
        return volService.registerVol(reqDto);
    }

    @GetMapping("/volunteer/get_comment_list")
    public List<VolCommentListDto> getCommentList(@RequestParam Long volunteerId)
    {
        return commentService.checkCommentsList(volunteerId);
    }

    @PostMapping("/volunteer/write_comment")
    public ResponseDataDto getWriteComment(@RequestBody VolUploadCommentDto reqDto)
    {
        return commentService.writeComment(reqDto);
    }

    @GetMapping("/volunteer/get_my_completed_volList")
    public List<VolListDto> getMyCompletedVolList(@RequestParam Long whoVol)
    {
        return volService.checkMyCompletedVolList(whoVol);
    }

}
