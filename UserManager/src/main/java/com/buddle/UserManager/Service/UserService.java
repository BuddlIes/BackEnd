package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Dto.UserLoginRequestDto;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //회원가입 확인
    public String join(UserJoinRequestDto users) {
        Optional<UserInfo> optUserId = userRepository.findById(users.getUser_number());
        Optional<UserInfo> optUserEmail = userRepository.findByEmail(users.getAjou_email());
        Optional<UserInfo> optWallet = userRepository.findByWallet(users.getWallet_address());
        Optional<UserInfo> optNickname = userRepository.findByNickname(users.getUser_nickname());

        if(optUserId.isEmpty() && optUserEmail.isEmpty() && optWallet.isEmpty() && optNickname.isEmpty()){ //같은 게 없으면 (회원가입 성공)

            //초기 설정
            UserInfo userEntity = users.toEntity();
            userEntity.setDelete_yn(0); // 탈퇴 안함
            userEntity.setCreated_at(LocalDateTime.now());
            userEntity.setVol_hour(0F);
            userEntity.setVol_num(0L);
            userEntity.setTemperature(36.5);
            userEntity.setCompliment_num(0L);
            userEntity.setAlarm(1); // 알람 ON

            userRepository.save(users.toEntity());
            return "Join Completed";
        }
        else { //회원가입 실패
            return "Cannot Join";
        }
    }

    //로그인 확인
    public String login(UserLoginRequestDto users) {
        Optional<UserInfo> optUserInfo = userRepository.findByEmail(users.getAjou_email());
        if(optUserInfo.isPresent()){ //이메일이 있으면
            UserInfo userInfo = optUserInfo.get();

            //데베에서 가져온 비번과 입력받은 비번 비교
            if (userInfo.getPassword().equals(users.getPassword())) {
                return "Login Completed";
            }
            else {
                return "Info is Wrong";
            }
        }
        else {
            return "Email is not found";
        }
    }
}
