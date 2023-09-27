package com.buddle.UserManager.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//회원 객체
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(unique = true)
    private Long user_number; //학번 및 사번 //중복X

    @Column private String name;
    @Column(unique = true) private String email; //중복X
    @Column private String password;
    @Column(unique = true) private String wallet; //중복X
    @Column(unique = true) private String nickname; //중복X

    @Column private Integer delete_yn; //탈퇴 1 & 미탈퇴 0
    @Column private Long created_data; //가입 일시
    @Column private Long recent_access; //최근 로그인 시
    @Column private String modified_data; //회원정보 수정일시

    @Column private Long vol_hour; //봉사시간
    @Column private Long vol_num; //봉사횟수

    @Column private Integer temperature; // 봉사 온도
    @Column private Integer compliment_num;
    @Column private Integer alarm; //알림 기능 on 1 & off 0

}
