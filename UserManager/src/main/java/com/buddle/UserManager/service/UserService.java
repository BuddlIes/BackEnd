package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.UserJoinRequestDto;
import com.buddle.UserManager.dto.UserLoginRequestDto;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.UserRepository;
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
            userEntity.setCreated_at(LocalDateTime.now()); //가입 일시
            //userEntity.setRecent_aceess(LocalDateTime.now()); // null
            userEntity.setModified_data(LocalDateTime.now()); //회원정보 수정일시

            userEntity.setVol_hour(0F); //총 봉사 시간
            userEntity.setVol_num(0L); //총 봉사 횟수

            userEntity.setTemperature(36.5); //초기 봉사 온도
            userEntity.setCompliment_num(0L); // 초기 칭찬받은 횟수
            userEntity.setAlarm(1); // 알람 ON
            userEntity.setLogin_num(0L); //로그인 횟수

            userRepository.save(userEntity);
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
            if (userInfo.getPassword().equals(users.getPassword())) { //로그인 성공
                Long loginNumber = userInfo.getLogin_num() + 1; //로그인 횟수 1++
                userInfo.setLogin_num(loginNumber);

                userInfo.setRecent_access(LocalDateTime.now());

                userRepository.save(userInfo);
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
