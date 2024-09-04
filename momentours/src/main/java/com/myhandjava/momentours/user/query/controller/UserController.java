package com.myhandjava.momentours.user.query.controller;

import com.myhandjava.momentours.user.command.domain.vo.user.ResponseUserIdVO;
import com.myhandjava.momentours.user.command.domain.vo.user.ResponseUserPwdVO;
import com.myhandjava.momentours.user.query.dto.UserDTO;
import com.myhandjava.momentours.user.query.dto.UserInfoDTO;
import com.myhandjava.momentours.user.query.dto.UserInquiryDTO;
import com.myhandjava.momentours.user.query.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("queryUserController")
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/searchById/{userEmail}")
    public String findUserById(@PathVariable String userEmail) {
        // user id로 정보 조회
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo = userService.searchUserWithId(userEmail);

        return userInfo.toString();
    }

    @GetMapping("/searchByNickname/{userNickname}")
    public String searchUserByNickname(@PathVariable String userNickname) {
        // usernickname으로 정보 조회
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo = userService.searchByNickname(userNickname);

        return userInfo.toString();
    }

    @GetMapping("/searchUserByAdmin/{userEmail}")
    public String searchUserByAdmin(@PathVariable String userEmail) {
        // Admin이 회원 정보 조회 - 관리용 정보 노출
        UserDTO userInfo = new UserDTO();
        userInfo = userService.getUserInfoByAdmin(userEmail);

        return userInfo.toString();
    }

    @GetMapping("/findUserPwdByEmail/{userEmail}")
    public String findUserPwdByEmail(@PathVariable String userEmail) {
        // Email로 Pwd 조회
        UserDTO userInfo = new UserDTO();
        userInfo = userService.getUserPwdByEmail(userEmail);

        ResponseUserPwdVO userPwdVO = new ResponseUserPwdVO();
        userPwdVO.setUserPwd(userInfo.getUserPwd());

        // Pwd 마스킹 처리
        String maskedPwd = userInfo.getUserPwd().substring(0, 4) + "********";
        userPwdVO.setUserPwd(maskedPwd);

        return userPwdVO.toString();
    }

    @GetMapping("/getInquiryPostByUserNo/{userNo}")
    public List<UserInquiryDTO> getInquiryPostByUserNo(@PathVariable int userNo) {
        // 회원별 문의글 확인 기능
        List<UserInquiryDTO> userInquiryDTO = new ArrayList<>();
        userInquiryDTO = userService.getUserInquiry(userNo);

        return userInquiryDTO;
    }

    @GetMapping("/findUserIdByPhone/{userPhone}")
    public ResponseUserIdVO findUserIdByPhone(@PathVariable String userPhone) {
        // 전화번호로 아이디 찾기
        ResponseUserIdVO responseUserIdVO = userService.getUserIdByPhone(userPhone);

        return responseUserIdVO;
    }


}
