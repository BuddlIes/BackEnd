package com.buddle.UserManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message_info")
public class MessageInfo {

    public enum MessageType{
        ENTER, TALK, LEAVE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id; //메세지 아이

    @Column private MessageType type;

    @Column private String roomId;
    @Lob private String message;
    @Column private Long sender; //학번
    @Column private LocalDateTime time;
}
