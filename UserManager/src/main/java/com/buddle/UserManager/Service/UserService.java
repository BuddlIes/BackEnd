package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Dto.UserLoginRequestDto;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

        if(optUserId.isEmpty() && optUserEmail.isEmpty() && optWallet.isEmpty() && optNickname.isEmpty()){ //같은 게 없으면
            userRepository.save(users.toEntity());
            return "Join Completed";
        }
        else {
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
