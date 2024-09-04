package com.myhandjava.momentours.user.command.application.service;

import com.myhandjava.momentours.user.command.application.dto.ModifyUserInfoDTO;
import com.myhandjava.momentours.user.command.application.dto.UserDTO;
import com.myhandjava.momentours.user.command.domain.aggregate.User;
import com.myhandjava.momentours.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Qualifier("commandUserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO modifyUserInfo(int userNo, ModifyUserInfoDTO modifyUserInfoDTO) {
        Optional<User> optionalUser = getUserById(userNo);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            // DTO의 값으로 User 객체 업데이트

            if (modifyUserInfoDTO.getUser_nickname() != null) {
                user.setUserNickname(modifyUserInfoDTO.getUser_nickname());
            }
            if (modifyUserInfoDTO.getUser_phone() != null) {
                user.setUserPhone(modifyUserInfoDTO.getUser_phone());
            }
            if (modifyUserInfoDTO.getUser_birth() != null) {
                user.setUserBirth(modifyUserInfoDTO.getUser_birth());
            }
            if (modifyUserInfoDTO.getUser_mbti() != null) {
                user.setUserMBTI(modifyUserInfoDTO.getUser_mbti());
            }
            if (modifyUserInfoDTO.getUser_pwd() != null) {
                user.setUserPwd(modifyUserInfoDTO.getUser_pwd());
            }

            user.setUserUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            UserDTO UpdatedUserInfo = new UserDTO(user);
            // User 객체를 UserDTO로 변환하여 반환
            return UpdatedUserInfo;


        } else {
            throw new RuntimeException(userNo + "번 유저의 정보를 가져오지 못하였습니다.");
        }
    }

    private Optional<User> getUserById(int userNo) {
        // UserNo에 해당하는 Optional<User> 객체 반환
        return userRepository.findById(userNo);
    }

}
