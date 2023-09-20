package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findById(Long user_number);
    Optional<UserInfo> findByName(String user_name);

    Optional<UserInfo> findByEmail(String ajou_email);
    List<UserInfo> findAll();
}
