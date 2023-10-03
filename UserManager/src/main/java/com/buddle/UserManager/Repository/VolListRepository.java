package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.ChatListInfo;
import com.buddle.UserManager.Entity.MessageInfo;
import com.buddle.UserManager.Entity.VolunteerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VolListRepository extends JpaRepository<VolunteerInfo, Long> {

    Optional<VolunteerInfo> findById(Long volunteerId);

    List<VolunteerInfo> findAll();

    List<VolunteerInfo> findTopByVolunteerIdOrderByWriteTime(Long volunteerId);

    /* 완료된 봉사는 제거하는 쿼리 추가해야함! */
    @Query("Select m from VolunteerInfo m where m.hashtag = :hashTag order by m.writeTime")
    List<VolunteerInfo> findByhashTagOrderByWriteTime(@Param("hashTag") String hashTag);
}
