package com.buddle.UserManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chatList_info")
public class ChatListInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long chatRoom_id;

    @Column private Long user1_num;
    @Column private Long user2_num;
    @Column private Long user1_out_time;
    @Column private Long user2_out_time;
    /*
     채팅방을 나가지 않으면 시간=0이고, 채팅방을 user1이 나간다면, 나간 시간을 user1_out_time에 저장함
     만약 이상황에서 user2가 user1에게 메세지를 보내면 user1_out_time에 저장되어있던 시간은 변수에 저장하고, 0으로 바꿈
    * */
}
