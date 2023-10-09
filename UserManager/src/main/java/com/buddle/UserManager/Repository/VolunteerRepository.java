package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Entity.VolunteerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<VolunteerInfo, Long> {
    List<VolunteerInfo> findAllOrderByWriteTime(String hashTag);

    @Query("Select m from VolunteerInfo m where m.hashtag = :hashTag and m.completed = 1 order by m.writeTime")
    List<VolunteerInfo> findByhashTagOrderByWriteTime(@Param("hashTag") String hashTag);

    Optional<VolunteerInfo> findById(Long volunteerId);

}
