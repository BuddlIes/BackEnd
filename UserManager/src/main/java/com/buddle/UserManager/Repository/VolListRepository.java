package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.VolunteerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolListRepository extends JpaRepository<VolunteerInfo, Long> {

    Optional<VolunteerInfo> findById(Long volunteerId);

    List<VolunteerInfo> findAll();
}
