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
@Table(name = "chat_info")
public class ChatInfo {
    @Id
    @Column(unique = true)
    private Long chatRoom_id;

    @Column(unique = true) private Long message_id;
    @Column private String message_content;
    @Column private Long message_from;
    @Column private Long created_at;

    @Column private Long user1_number;
    @Column private Long user2_number;
    @Column private Long user1_out_time; //user1이 채팅방 나간(삭제한) 시간
    @Column private Long user2_out_time; //user2rk 채팅방 나간(삭제한) 시간
}
