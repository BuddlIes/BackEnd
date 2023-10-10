package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.MsgDto;
import com.buddle.UserManager.Dto.VolCommentDto;
import com.buddle.UserManager.Entity.MessageInfo;
import com.buddle.UserManager.Entity.VolCommentInfo;
import com.buddle.UserManager.Repository.ChatListRepository;
import com.buddle.UserManager.Repository.CommentRepository;
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
}
