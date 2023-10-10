package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.ChatListInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommnetRepository {
    @Query("Select m from VolCommentInfo m where m.volunteerId = :volId order by m.time")
    List<ChatListInfo> findChatRooms(@Param("volId") Long volId);
}
