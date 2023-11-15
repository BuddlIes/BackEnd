package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.VolCommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<VolCommentInfo, Long> {
    @Query("Select m from VolCommentInfo m where m.volunteerId = :volId order by m.id")
    List<VolCommentInfo> findComments(@Param("volId") Long volId);

    Long countByWhoCommEquals(Long whoComm);
}
