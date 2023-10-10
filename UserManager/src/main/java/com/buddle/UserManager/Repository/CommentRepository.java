package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.VolCommentInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository {
    @Query("Select m from VolCommentInfo m where m.volunteerId = :volId order by m.time")
    List<VolCommentInfo> findComments(@Param("volId") Long volId);
}
