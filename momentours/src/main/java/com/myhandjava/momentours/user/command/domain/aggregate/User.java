package com.myhandjava.momentours.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_user")
public class User {

    @Id
    @Column ( name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column (name ="user_email")
    private String userEmail;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column (name = "user_name")
    private String userName;

    @Column (name = "user_nickname")
    private String userNickname;

    @Column (name = "user_phone")
    private String userPhone;

    @Column (name = "user_birth")
    private LocalDateTime userBirth;

    @Column (name = "user_mbti")
    @Enumerated(EnumType.STRING)
    private UserMBTI userMBTI;     //  enum type

    @Column (name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;     //  enum type

    @Column ( name = "user_gender")
    @Enumerated(EnumType.STRING)
    private UserGender userGender; //  enum type

    @Column (name = "accessible_date")
    private LocalDateTime accessibleDate;

    @Column (name = "user_report_count")
    private int userReportCount;

    @Column (name = "user_create_at")
    private LocalDateTime userCreatedAt;

    @Column (name = "user_update_at")
    private LocalDateTime userUpdatedAt;


}
