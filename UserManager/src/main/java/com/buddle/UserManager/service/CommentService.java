package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.VolCommentDto;
import com.buddle.UserManager.entity.VolCommentInfo;
import com.buddle.UserManager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<VolCommentDto> checkCommentsList(Long volunteerId) {
        List<VolCommentInfo> commentInfos = commentRepository.findComments(volunteerId);

        List<VolCommentDto> commentDtoList = commentInfos.stream().map(
                m-> new VolCommentDto(m.getId(),m.getVolunteerId(),m.getComments(),m.getWhoComm(),m.getTime())
        ).collect(Collectors.toList());

        return commentDtoList;
    }

    public String writeComment(VolCommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());

        return "Write Completed";
    }
}
