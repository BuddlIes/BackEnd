package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//회원 레포지토리 인터페이스
public interface UserRepository extends JpaRepository<UserInfo, Long > {

    Optional<UserInfo> findById(Long user_number);
    Optional<UserInfo> findByEmail(String ajou_email);
    Optional<UserInfo> findByWallet(String wallet_address);
    Optional<UserInfo> findByNickname(String user_nickname);

    List<UserInfo> findAll();
}
