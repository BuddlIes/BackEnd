package com.buddle.UserManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user_chatlist")
public class User_ChatList {
    @Id
    @Column
    private Long chatRoom_id;

    @Column private Long user_number1;
    @Column private Long user_number2;
    @Column private Long user1_out_time;
    @Column private Long user2_out_time;
}
