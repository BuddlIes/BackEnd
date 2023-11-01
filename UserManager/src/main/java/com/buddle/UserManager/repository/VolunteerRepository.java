package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.VolunteerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<VolunteerInfo, Long> {
    //전체 게시물 리스트 작성된 시간순으로 확인
    List<VolunteerInfo> findAllOrderByWriteTime(String hashTag);

    //사용자가 해시태그별로 게시물 리스트 작성된 시간순으로 확인
    @Query("Select m from VolunteerInfo m where m.hashtag = :hashTag and m.completed = 1 order by m.writeTime")
    List<VolunteerInfo> findByHashTagOrderByWriteTime(@Param("hashTag") String hashTag);

    Optional<VolunteerInfo> findById(Long volunteerId);

    //사용자가 완료한 봉사 내역 리스트 봉사 시간 순으로 확인
    @Query("Select m from VolunteerInfo m where m.whoVol = :whoVol and m.completed = 0 order by m.whenVol")
    List<VolunteerInfo> findByWhoVolOrderByWriteTime(@Param("whoVol") Long whoVol);

    //사용자가 완료한 봉사 활동 시간 다 더하기

    //사용자가 완료한 봉사 횟수(completed = 0 넣어서 호출하면 됨)
    Long countDistinctByWriterEqualsAndCompletedEquals(Long writer, Integer completed);

}
