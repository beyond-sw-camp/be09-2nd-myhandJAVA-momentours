package com.myhandjava.momentours.user.command.application.dto;

import com.myhandjava.momentours.user.command.domain.aggregate.UserMBTI;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModifyUserInfoDTO {

    private String user_nickname;
    private String user_phone;
    private LocalDateTime user_birth;
    private UserMBTI user_mbti;
    private String user_pwd;

}
