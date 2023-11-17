package com.buddle.UserManager.service;

import com.buddle.UserManager.UserManagerApplication;
import com.buddle.UserManager.dto.LoginRequestResponseDto;
import com.buddle.UserManager.dto.ResponseDataDto;
import com.buddle.UserManager.dto.UserJoinRequestDto;
import com.buddle.UserManager.dto.UserLoginRequestDto;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.UserRepository;
import com.buddle.UserManager.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    //회원가입 확인
    public String join(UserJoinRequestDto users) {

        Optional<UserInfo> optUserId = userRepository.findById(users.getUser_number());
        Optional<UserInfo> optUserEmail = userRepository.findByEmail(users.getEmail());
        Optional<UserInfo> optWallet = userRepository.findByWallet(users.getWallet());
        Optional<UserInfo> optNickname = userRepository.findByNickname(users.getNickname());
        System.out.println(users.getName());
        if(optUserId.isEmpty() && optUserEmail.isEmpty() && optWallet.isEmpty() && optNickname.isEmpty()){ //같은 게 없으면 (회원가입 성공)

            //초기 설정
            UserInfo userEntity = users.toEntity();
            System.out.println(userEntity.getName());
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
    public ResponseDataDto<LoginRequestResponseDto> login(UserLoginRequestDto users) {

        Optional<UserInfo> optUserEmail = userRepository.findByEmail(users.getEmail());
        LoginRequestResponseDto loginRequestResponseDto = null;

        if(optUserEmail.isPresent()){ //이메일이 있으면
            UserInfo userInfo = optUserEmail.get();

            //데베에서 가져온 비번과 입력받은 비번 비교
            if (userInfo.getPassword().equals(users.getPassword())) { //로그인 성공
                final String token = tokenProvider.create(optUserEmail.get());

                System.out.println(userInfo.toString());
                Long loginNumber = userInfo.getLogin_num() + 1; //로그인 횟수 1++
                userInfo.setLogin_num(loginNumber);

                userInfo.setRecent_access(LocalDateTime.now());
                System.out.println(optUserEmail);

                userRepository.save(userInfo);
                return new ResponseDataDto("Login Success", 200, new LoginRequestResponseDto(optUserEmail.get().getUser_number(), optUserEmail.get().getName(), optUserEmail.get().getEmail(), optUserEmail.get().getNickname(), token));
            }
            else {
                return new ResponseDataDto("Info is wrong", 406, null);
            }
        }
        else {
            return new ResponseDataDto("Email is not found", 406, null);
        }
    }

    public String checkNickname(Long userId) {
        // UserRepository에서 userId에 해당하는 UserInfo를 찾습니다.
        Optional<UserInfo> userInfoOptional = userRepository.findById(userId);

        // UserInfo가 존재하면 해당 UserInfo의 nickname을 반환합니다.
        // 존재하지 않을 경우 예외 처리를 추가하거나, 기본값을 반환하도록 수정할 수 있습니다.
        return userInfoOptional.map(UserInfo::getNickname).orElse(null);
    }

    public Double checkTemp(Long userId) {
        // UserRepository에서 userId에 해당하는 UserInfo를 찾습니다.
        Optional<UserInfo> userInfoOptional = userRepository.findById(userId);

        // UserInfo가 존재하면 해당 UserInfo의 nickname을 반환합니다.
        // 존재하지 않을 경우 예외 처리를 추가하거나, 기본값을 반환하도록 수정할 수 있습니다.
        return userInfoOptional.map(UserInfo::getTemperature).orElse(null);
    }
}
