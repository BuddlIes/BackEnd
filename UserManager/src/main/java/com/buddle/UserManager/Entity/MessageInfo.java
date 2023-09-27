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
@Table(name = "message_info")
public class MessageInfo {
    @Id
    @Column(unique = true)
    private Long id;

    @Column private Long chatRoom_id;
    @Column private Long message_id;
    @Column private String message_content;
    @Column private Long created_at;
}
