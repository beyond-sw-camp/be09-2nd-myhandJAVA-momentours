package com.myhandjava.momentours.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Inquiry {

    // 임시로 내 도메인에서 임시 생성한 Entity 활용하여 조회. 추후 Inquiry 도메인 Entity로 매핑해줘야함.
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inquiryNo;

    @Column
    private int UserNo;

    @Column
    private String Title;

    @Column
    private String Content;

    @Column
    private String answer;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private boolean isDeleted;

}
