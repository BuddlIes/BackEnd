package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.MemoryUserRepository;
import com.buddle.UserManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    //회원가입 확인
    public String join(UserJoinRequestDto users) {
        Optional<UserInfo> optUserInfo = userRepository.findById(users.getUser_number());
        if(optUserInfo.isEmpty()){ //같은 게 없으면
            userRepository.save(users.toEntity());
            return "Completed";
        }
        else {
            return "Already Registered";
        }
    }


//    //회원가입
//    public Long join(UserInfo users) {
//        validateDuplicateUser(users); //중복 회원 검증
//        userRepository.save(users);
//        return users.getUser_number();
//    }
//
//    private void validateDuplicateUser(UserInfo users) {
//        userRepository.findByName(users.getUser_name());
//    }
//
//    //회원 전체 조회
//    public List<UserInfo> findUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<UserInfo> findOne(Long userId) {
//        return userRepository.findById(userId);
//    }
}
