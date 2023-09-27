package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.ChatListInfo;
import com.buddle.UserManager.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatListRepository extends JpaRepository<ChatListInfo, Long> {
//    Optional<ChatListInfo> findByRoomId(Long chatRoom_id);
//    Optional<ChatListInfo> findByUser1(Long user1_num);
//    Optional<ChatListInfo> findByUser2(Long user2_num);

    List<ChatListInfo> findAll();


    @Query("Select m from ChatListInfo m where m.user1_num = :member_num or m.user2_num = :member_num")
    List<ChatListInfo> findChatRooms(@Param("member_num") Long member_num);
}
