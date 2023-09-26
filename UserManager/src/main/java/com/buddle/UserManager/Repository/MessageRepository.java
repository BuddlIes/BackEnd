package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageInfo, Long> {
//    Optional<MessageInfo> findByChatId(Long chatRoom_id);
//    Optional<MessageInfo> findByMsgId(Long message_id);
//
//    Optional<MessageInfo> findByMsgContent(String message_content);
//    Optional<MessageInfo> findByCreatedTime(Long created_at);
    List<MessageInfo> findAll();

    List<MessageInfo> findTopByChatroomidOrderByCreatedatDesc(Long chatRoom_id);
//    @Query("Select m from MessageInfo m where chatRoom_id = chatRoom_id order by m.created_at desc limit 1")
//    Optional<MessageInfo> findLastMessageFromRoom(@Param("chatRoom_id") Long chatRoom_id);
}
