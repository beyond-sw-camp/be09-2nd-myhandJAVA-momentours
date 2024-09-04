package com.myhandjava.momentours.user.command.application.controller;

import com.myhandjava.momentours.user.command.application.dto.ModifyUserInfoDTO;
import com.myhandjava.momentours.user.command.application.dto.UserDTO;
import com.myhandjava.momentours.user.command.domain.aggregate.User;
import com.myhandjava.momentours.user.command.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("commandUserController")
@RequestMapping("/userdml")
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/modifyUserInfo/{userNo}")
    public ResponseEntity<UserDTO> modifyUserInfo(@PathVariable int userNo , @RequestBody ModifyUserInfoDTO ModifyUserInfoDTO) {

        UserDTO updatedUser = userService.modifyUserInfo(userNo, ModifyUserInfoDTO);

        return ResponseEntity.ok(updatedUser);
    }
}
