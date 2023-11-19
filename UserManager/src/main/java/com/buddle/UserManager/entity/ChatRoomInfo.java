package com.buddle.UserManager.entity;

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
public class ChatRoomInfo {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private String roomId;

    @Column private String roomName;

    @Column private Long sender1;
    @Column private Long sender2;
//    @Column private LocalDateTime user1_out_time;
//    @Column private LocalDateTime user2_out_time;
    /*
     채팅방을 나가지 않으면 시간=0이고, 채팅방을 user1이 나간다면, 나간 시간을 user1_out_time에 저장함
     만약 이상황에서 user2가 user1에게 메세지를 보내면 user1_out_time에 저장되어있던 시간은 변수에 저장하고, 0으로 바꿈
    * */
}
