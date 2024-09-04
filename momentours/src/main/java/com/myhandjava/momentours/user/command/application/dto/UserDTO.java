package com.myhandjava.momentours.user.command.application.dto;

import com.myhandjava.momentours.user.command.domain.aggregate.User;
import com.myhandjava.momentours.user.command.domain.aggregate.UserGender;
import com.myhandjava.momentours.user.command.domain.aggregate.UserMBTI;
import com.myhandjava.momentours.user.command.domain.aggregate.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Data
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

    public UserDTO(User user) {
        // Update 시 User -> UserDTO 변환용 생성자
        this.userNo = user.getUserNo();
        this.userEmail = user.getUserEmail();
        this.userPwd = user.getUserPwd();
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickname();
        this.userPhone = user.getUserPhone();
        this.userBirth = user.getUserBirth();
        this.userMBTI = user.getUserMBTI();
        this.userRole = user.getUserRole();
        this.userGender = user.getUserGender();
        this.accessibleDate = user.getAccessibleDate();
        this.userReportCount = user.getUserReportCount();
        this.userCreatedAt = user.getUserCreatedAt();
        this.userUpdatedAt = user.getUserUpdatedAt();
    }

}
