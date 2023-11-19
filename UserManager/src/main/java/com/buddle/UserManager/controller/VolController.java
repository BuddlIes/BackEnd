package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.service.CommentService;
import com.buddle.UserManager.service.UserService;
import com.buddle.UserManager.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VolController {

    @Autowired
    VolService volService;

    @Autowired
    UserService userService;

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

        //사용자의 닉네임
        String userNickname = userService.checkNickname(whoVol);
        result.put("userNickname", userNickname);

        Double userTemperature = userService.checkTemp(whoVol);
        result.put("userTemperature", userTemperature);

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

    @PostMapping("/volunteer/when_vol_completed")
    public String getVolCompletedInfo(@RequestBody VolunteerCompletedRequestDto reqDto)
    {
        return volService.uploadVolInfoWhenCompleted(reqDto);
    }

    @GetMapping("/volunteer/get_my_applied_volInfo")
    public Map<String, Object> getMyAppliedVolInfo(@RequestParam Long whoVol)
    {
        Map<String, Object> result = new HashMap<>();

        // 사용자가 신청한 봉사정보 리스트
        List<VolListDto> myVolList = volService.checkMyAppliedVolList(whoVol);
        result.put("appliedVolList", myVolList);

        return result;
    }

    @PostMapping("/volunteer/when_vol_applied")
    public String getVolApplied(@RequestBody VolunteerApplyRequestDto reqDto)
    {
        return volService.uploadVolInfoWhenApplied(reqDto);
    }

}
