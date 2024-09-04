package com.myhandjava.momentours.user.query.dto;

import java.time.LocalDateTime;
import com.myhandjava.momentours.user.command.domain.aggregate.UserMBTI;
import com.myhandjava.momentours.user.command.domain.aggregate.UserRole;
import com.myhandjava.momentours.user.command.domain.aggregate.UserGender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {

    // user 도메인 기본 DTO
    private int userNo;
    private String userEmail;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userPhone;
    private LocalDateTime userBirth;
    private UserMBTI userMBTI;     //  enum type
    private UserRole userRole;     //  enum type
    private UserGender userGender; //  enum type
    private LocalDateTime accessibleDate;
    private int userReportCount;
    private LocalDateTime userCreatedAt;
    private LocalDateTime userUpdatedAt;


    // 앞으로 로직에 필요한 생성자 만들기


}
