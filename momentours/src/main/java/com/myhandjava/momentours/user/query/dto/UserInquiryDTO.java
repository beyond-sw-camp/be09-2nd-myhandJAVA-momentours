package com.myhandjava.momentours.user.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInquiryDTO {

    // 문의 Response용 DTO
    private int inquiryNo;
    private String Title;
    private String Content;
    private String answer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;


}
