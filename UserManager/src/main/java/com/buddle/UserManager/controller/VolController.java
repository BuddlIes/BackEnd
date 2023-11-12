package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.service.CommentService;
import com.buddle.UserManager.service.VolService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    @RequestMapping(value = "/volunteer/register_vol", method = RequestMethod.POST, consumes = "multipart/form-data")
    @PostMapping("/volunteer/register_vol")
    public ResponseDataDto registerVolunteer(@RequestBody VolUploadRequestDto reqDto)
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

    @GetMapping("/volunteer/get_my_completed_volInfo")
    public Map<String, Object> getMyCompletedVolInfo(@RequestParam Long whoVol)
    {
        Map<String, Object> result = new HashMap<>();

        // 사용자가 완료한 봉사정보 리스트
        List<VolListDto> myVolList = volService.checkMyCompletedVolList(whoVol);
        result.put("completedVolList", myVolList);

        // 사용자가 완료한 총 봉사시간
        Float totalVolunteerTime = volService.checkMyVolTime(whoVol);
        result.put("totalVolunteerTime", totalVolunteerTime);

        // 사용자가 완료한 총 봉사 횟수
        Integer totalVolunteerCount = volService.checkMyVolNum(whoVol);
        result.put("totalVolunteerCount", totalVolunteerCount);

        return result;
    }

}
