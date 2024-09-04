package com.myhandjava.momentours.user.command.application.service;


import com.myhandjava.momentours.user.command.application.dto.ModifyUserInfoDTO;
import com.myhandjava.momentours.user.command.application.dto.UserDTO;
import com.myhandjava.momentours.user.command.domain.aggregate.User;

public interface UserService {


    UserDTO modifyUserInfo(int userNo, ModifyUserInfoDTO modifyUserInfoDTO);
}
