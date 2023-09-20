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
    @Column
    private Long user_number; //학번 및 사번 //중복X

    @Column private String name;
    @Column private String ajou_email; //중복X
    @Column private String login_id; //중복X
    @Column private String password;
    @Column private String wallet_address; //중복X
    @Column private String user_nickname; //중복X

    @Column private Integer delete_yn; //0 or 1
    @Column private String created_data;
    @Column private String modified_data;

    @Column private Long vol_hour;
    @Column private Long vol_num;

    @Column private Double temperature;
    @Column private Integer compliment_num;
    @Column private Integer alarm;

}
