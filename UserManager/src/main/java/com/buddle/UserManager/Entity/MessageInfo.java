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

    @Column private Long chatroomid;
    @Column private Long messageid;
    @Column private String messagecontent;
    @Column private Long messagefrom;
    @Column private Long createdat;
}
