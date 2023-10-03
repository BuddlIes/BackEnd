package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Dto.StampDto;
import com.buddle.UserManager.Entity.StampInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<StampInfo, Long> {

}
