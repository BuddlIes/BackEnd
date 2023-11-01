package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.ChatListInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatListRepository extends JpaRepository<ChatListInfo, Long> {

    List<ChatListInfo> findAll();


    @Query("Select m from ChatListInfo m where m.user1_num = :member_num or m.user2_num = :member_num")
    List<ChatListInfo> findChatRooms(@Param("member_num") Long member_num);
}
