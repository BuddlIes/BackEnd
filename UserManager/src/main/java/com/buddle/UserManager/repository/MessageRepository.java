package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageInfo, Long> {

    List<MessageInfo> findAll();

    List<MessageInfo> findTopByChatroomidOrderByCreatedatDesc(Long chatRoom_id);

    List<MessageInfo> findByChatroomidOrderByCreatedat(Long chatRoom_id);

//    @Query("Select m from MessageInfo m where chatRoom_id = chatRoom_id order by m.created_at desc limit 1")
//    Optional<MessageInfo> findLastMessageFromRoom(@Param("chatRoom_id") Long chatRoom_id);
}
