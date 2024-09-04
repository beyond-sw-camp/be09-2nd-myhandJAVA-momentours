package com.myhandjava.momentoursUser.query.service;

import com.myhandjava.momentoursUser.client.MomentoursClient;
import com.myhandjava.momentoursUser.command.applicaiton.dto.*;
import com.myhandjava.momentoursUser.command.domain.aggregate.UserEntity;
import com.myhandjava.momentoursUser.command.domain.vo.ResponseUserIdVO;
import com.myhandjava.momentoursUser.common.ResponseMessage;
import com.myhandjava.momentoursUser.query.repository.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTests {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MomentoursClient momentoursClient;

    @Test
    @DisplayName("findAllUsersTest")
    void findAllUsersTest() {

        //List 불러오기
        List<UserDTO> userDTOList = userService.findAllUsers();

        //테스트
        Assertions.assertNotNull(userDTOList);
        Assertions.assertFalse(userDTOList.isEmpty());
        userDTOList.forEach(System.out::println);

    }

    @Test
    @DisplayName("findByUserEmail")
    void findByUserEmailTest() {
        //테스트 객체 생성 및 초기화
        UserEntity testUserInfo = new UserEntity();
        testUserInfo.setUserEmail("user1@example.com");

        //List 불러오기
        List<UserDTO> userDTOList = userService.findAllUsers();

        //테스트
        UserDTO userDTO = userDTOList.get(0);
        assertEquals("user1@example.com", userDTO.getEmail(), "Email 비교 결과가 불일치합니다!!");
    }

    @Test
    @DisplayName("viewMyPageTest")
    void viewMyPageTest() {

        // 테스트 객체 생성 및 초기화
        ScheduleDTO testSchedule = new ScheduleDTO();
        testSchedule.setCoupleNo(1);
        testSchedule.setScheduleNo(1);
        testSchedule.setScheduleTitle("서울 여행");

        // 스케줄 List 불러오기
        ResponseEntity<ResponseMessage> scheduleResponse = momentoursClient.findAllSchedule(1);
        UserMyPageDTO userMyPageDTO = new UserMyPageDTO();
        userMyPageDTO.setScheduleList((List<ScheduleDTO>) scheduleResponse.getBody().getResult().get("ScheduleList"));

        List<ScheduleDTO> actualScheduleList = userMyPageDTO.getScheduleList();

        //테스트
        ScheduleDTO actualData = actualScheduleList.get(0);
        assertEquals(testSchedule.getCoupleNo(), actualData.getCoupleNo(), "These Values Are Not Match Each Other");
        assertEquals(testSchedule.getScheduleTitle(), actualData.getScheduleTitle(), "These Values Are Not Match Each Other");
    }

    @Test
    @DisplayName("searchUserWithIdTest")
    void searchUserWithIdTest() {

        // 테스트 객체 생성 및 초기화
        UserEntity testInfo = new UserEntity();
        testInfo.setUserNickname("길동이");

        // Email로 객체 불러오기
        String tempEmail = "user1@example.com";
        UserInfoDTO user = userService.searchUserWithId(tempEmail);

        // 테스트
        assertEquals(testInfo.getUserNickname(), user.getUserNickname(), " nickName 비교 결과가 동일하지 않습니다!! ");

    }

    @Test
    @DisplayName("searchByNickname")
    void searchByNicknameTest() {

        // 테스트 객체 생성 및 초기화
        UserEntity testInfo = new UserEntity();
        testInfo.setUserNickname("길동이");

        // nickname으로 객체 불러오기
        String nickname = "길동이";
        UserInfoDTO actualInfo = userService.searchByNickname(nickname);

        // 테스트
        assertEquals(testInfo.getUserNickname(), actualInfo.getUserNickname(), " Nickname 비교 결과가 동일하지 않습니다!! ");
    }

    @Test
    @DisplayName("getUserPwdByEmailTest")
    void getUserPwdByEmailTest() {
        UserEntity testPwdInfo = new UserEntity();
        testPwdInfo.setUserPwd("password1");

        String Email = "user1@example.com";
        UserEntityDTO actualPwdInfo = userService.getUserPwdByEmail(Email);

        assertEquals(testPwdInfo.getUserPwd(), actualPwdInfo.getUserPwd(), "Email을 할용한 Pwd 비교 결과가 동일하지 않습니다!");

    }

    @Test
    @DisplayName("getUserIdByPhone")
    void getUserIdByPhoneTest() {
        UserEntity testIdInfo = new UserEntity();
        testIdInfo.setUserEmail("user1@example.com");

        String phoneNum = "010-1234-5678";
        ResponseUserIdVO actualEmailInfo = userService.getUserIdByPhone(phoneNum);

        assertEquals(testIdInfo.getUserEmail(), actualEmailInfo.getUserEmail(), " PhoneNum을 활용한 Email 비교 결과가 동일하지 않습니다!");

    }
}







/**
    만들어야할 테스트 기능 목록
    - Query
 loadUserByUsername()
 findAllUsers ( OK )
 findByUserEmail ( OK )
 viewMyPage ( OK )
 searchUserWithId ( OK )
 searchByNickname ( OK )
 getUserPwdByEmail ( ing )
 getUserIdByPhone ( ing )

**/