package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.MessageInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendMsgDto {

    private Long chatroomid;
    private LocalDateTime createdat;
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
