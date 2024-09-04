package com.myhandjava.momentours.user.query.service;


import com.myhandjava.momentours.user.command.domain.vo.user.ResponseUserIdVO;
import com.myhandjava.momentours.user.query.dto.UserDTO;
import com.myhandjava.momentours.user.query.dto.UserInfoDTO;
import com.myhandjava.momentours.user.query.dto.UserInquiryDTO;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Qualifier("queryUserService")
public interface UserService {


    UserInfoDTO searchUserWithId(String userEmail);

    UserInfoDTO searchByNickname(String nickname);

    UserDTO getUserInfoByAdmin(String userEmail);

    UserDTO getUserPwdByEmail(String userEmail);

    List<UserInquiryDTO> getUserInquiry(int userNo);

    ResponseUserIdVO getUserIdByPhone(String userPhone);
}
