package com.myhandjava.momentours.user.query.dto;

import com.myhandjava.momentours.user.command.domain.aggregate.UserGender;
import com.myhandjava.momentours.user.command.domain.aggregate.UserMBTI;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserInfoDTO {
    // UserInfo 조회시 반환용 DTO
    private String userNickname;
    private UserMBTI userMBTI;     //  enum type
    private UserGender userGender;
}
