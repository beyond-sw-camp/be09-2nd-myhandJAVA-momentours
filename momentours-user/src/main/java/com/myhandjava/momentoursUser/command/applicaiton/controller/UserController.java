package com.myhandjava.momentoursUser.command.applicaiton.controller;

import com.myhandjava.momentoursUser.command.domain.vo.RequestUpdateUserVO;
import com.myhandjava.momentoursUser.common.ResponseMessage;
import com.myhandjava.momentoursUser.command.applicaiton.dto.UserDTO;
import com.myhandjava.momentoursUser.command.applicaiton.service.UserService;
import com.myhandjava.momentoursUser.command.domain.vo.RequestRegistUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandUserController")
public class UserController {
    private Environment env;
    private UserService userService;

    @Autowired
    public UserController(Environment env,
                          UserService userService){
        this.env = env;
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseMessage> registUser(@RequestBody RequestRegistUserVO newUser){

        UserDTO userDTO = requestResigtUserVOToUserDTO(newUser);
        userService.registUser(userDTO);

        ResponseMessage responseMessage = new ResponseMessage(201,"회원 가입 성공",null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PutMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable int userNo,
                                                      @RequestBody RequestUpdateUserVO requestUpdateUserVO){
        UserDTO updateUser = requestUpdateUserVOToUserDTO(requestUpdateUserVO);
        userService.updateUser(userNo,updateUser);

        ResponseMessage responseMessage = new ResponseMessage(200,"회원 정보 수정 성공",null);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable int userNo){
        userService.deleteUser(userNo);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    private UserDTO requestUpdateUserVOToUserDTO(RequestUpdateUserVO requestUpdateUserVO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(requestUpdateUserVO.getName());
        userDTO.setNickname(requestUpdateUserVO.getNickname());
        userDTO.setPhone(requestUpdateUserVO.getPhone());
        userDTO.setBirth(requestUpdateUserVO.getBirth());
        userDTO.setMbti(requestUpdateUserVO.getMbti());
        userDTO.setGender(requestUpdateUserVO.getGender());
        return userDTO;
    }


    private UserDTO requestResigtUserVOToUserDTO(RequestRegistUserVO newMember) {
        UserDTO userDTO = new UserDTO();
        userDTO.setBirth(newMember.getBirth());
        userDTO.setEmail(newMember.getEmail());
        userDTO.setGender(newMember.getGender());
        userDTO.setMbti(newMember.getMbti());
        userDTO.setName(newMember.getName());
        userDTO.setNickname(newMember.getNickname());
        userDTO.setPhone(newMember.getPhone());
        userDTO.setPwd(newMember.getPwd());
        userDTO.setUserRole(newMember.getUserRole());

        return userDTO;
    }



}
