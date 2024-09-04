package com.myhandjava.momentours.user.query.service;

import com.myhandjava.momentours.user.command.domain.aggregate.Inquiry;
import com.myhandjava.momentours.user.command.domain.aggregate.User;
import com.myhandjava.momentours.user.command.domain.vo.user.ResponseUserIdVO;
import com.myhandjava.momentours.user.query.dto.UserDTO;
import com.myhandjava.momentours.user.query.dto.UserInfoDTO;
import com.myhandjava.momentours.user.query.dto.UserInquiryDTO;
import com.myhandjava.momentours.user.query.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("queryUserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserInfoDTO searchUserWithId(String userEmail) {
        // Email로 조회
        User user = userMapper.searchUserWithEmail(userEmail);

        UserInfoDTO returnUserInfo = UserToUserDTO(user);

        return returnUserInfo;
    }

    @Override
    public UserInfoDTO searchByNickname(String userNickname) {
        // Nickname으로 조회
        User user = userMapper.searchUserWithNickname(userNickname);

        UserInfoDTO returnUserInfo = UserToUserDTO(user);

        return returnUserInfo;
    }

    @Override
    public UserDTO getUserInfoByAdmin(String userEmail) {

        User user = userMapper.getUserInfoWithEmail(userEmail);

        UserDTO userInfo = setUserInfoToDTO(user);

        return userInfo;
    }

    @Override
    public UserDTO getUserPwdByEmail(String userEmail) {
        User user = userMapper.getUserPwd(userEmail);

        UserDTO userPwd = setUserPwdToDTO(user);

        return userPwd;
    }

    @Override
    public List<UserInquiryDTO> getUserInquiry(int userNo) {
        List<Inquiry> userInquiry = userMapper.getInquiryList(userNo);

        return userInquiry.stream()
                .map(inquiry -> {
                    UserInquiryDTO inquiryInfo = new UserInquiryDTO();
                    inquiryInfo.setInquiryNo(inquiry.getInquiryNo());
                    inquiryInfo.setTitle(inquiry.getTitle());
                    inquiryInfo.setContent(inquiry.getContent());
                    inquiryInfo.setAnswer(inquiry.getAnswer());
                    inquiryInfo.setCreatedAt(inquiry.getCreatedAt());
                    inquiryInfo.setUpdatedAt(inquiry.getUpdatedAt());
                    return inquiryInfo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseUserIdVO getUserIdByPhone(String userPhone) {
        User user = userMapper.getUserIdWithPhone(userPhone);

        ResponseUserIdVO Result = UserToResponseUserIdVO(user);

        return Result;
    }


    private ResponseUserIdVO UserToResponseUserIdVO(User user) {
        // getUserIdByPhone() 타입 변환용 메소드
        ResponseUserIdVO returnValue = new ResponseUserIdVO();
        returnValue.setUserEmail(user.getUserEmail());

        return returnValue;
    }

    private UserDTO setUserPwdToDTO(User user) {
        // UserPwd set 메소드
        UserDTO userPwdInfo = new UserDTO();

        userPwdInfo.setUserPwd(user.getUserPwd());

        return userPwdInfo;
    }

    private UserDTO setUserInfoToDTO(User user) {
        // For Admin 회원 정보 조회용 메소드
        UserDTO userInfo = new UserDTO();
        userInfo.setUserBirth(user.getUserBirth());
        userInfo.setUserEmail(user.getUserEmail());
        userInfo.setUserNo(user.getUserNo());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserPhone(user.getUserPhone());
        userInfo.setUserRole(user.getUserRole());
        userInfo.setUserGender(user.getUserGender());
        userInfo.setUserMBTI(user.getUserMBTI());
        userInfo.setUserNickname(user.getUserNickname());
        userInfo.setUserReportCount(user.getUserReportCount());
        userInfo.setAccessibleDate(user.getAccessibleDate());
        userInfo.setUserCreatedAt(user.getUserCreatedAt());
        userInfo.setUserUpdatedAt(user.getUserUpdatedAt());

        return userInfo;
    }

    private UserInfoDTO UserToUserDTO(User user) {
        /* 매핑용 메소드 */
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setUserNickname(user.getUserNickname());
        userInfoDTO.setUserMBTI(user.getUserMBTI());
        userInfoDTO.setUserGender(user.getUserGender());

        return userInfoDTO;
    }
}

