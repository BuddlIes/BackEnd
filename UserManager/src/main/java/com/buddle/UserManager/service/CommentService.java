package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.ResponseDataDto;
import com.buddle.UserManager.dto.VolCommentListDto;
import com.buddle.UserManager.dto.VolCommentRequestResponseDto;
import com.buddle.UserManager.dto.VolUploadCommentDto;
import com.buddle.UserManager.entity.VolCommentInfo;
import com.buddle.UserManager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<VolCommentListDto> checkCommentsList(Long volunteerId) {
        List<VolCommentInfo> commentInfos = commentRepository.findComments(volunteerId);

        List<VolCommentListDto> commentDtoList = commentInfos.stream().map(
                m-> new VolCommentListDto(m.getId(),m.getComments(),m.getWhoComm(), m.getTime())
        ).collect(Collectors.toList());

        return commentDtoList;
    }

    public ResponseDataDto<VolCommentRequestResponseDto> writeComment(VolUploadCommentDto commentDto) {

        VolCommentInfo volCommentInfo = commentDto.toEntity();
        volCommentInfo.setTime(LocalDateTime.now());
        commentRepository.save(volCommentInfo);

        return new ResponseDataDto("Upload Comment Success", 200, new VolCommentRequestResponseDto(volCommentInfo.getId(), volCommentInfo.getWhoComm(), volCommentInfo.getTime()));
    }
}
