package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendMsgDto {

    private Long chatroomid;
    private Long createdat;
    private String messagecontent;
    private Long messagefrom;

    public MessageInfo toEntity()
    {
        MessageInfo msgInfo = new MessageInfo();

        msgInfo.setChatroomid(this.chatroomid);
        msgInfo.setCreatedat(this.createdat);
        msgInfo.setMessagecontent(this.messagecontent);
        msgInfo.setMessagefrom(this.messagefrom);

        return msgInfo;
    }
}
