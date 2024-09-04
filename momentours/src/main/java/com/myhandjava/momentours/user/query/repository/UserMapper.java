package com.myhandjava.momentours.user.query.repository;

import com.myhandjava.momentours.user.command.domain.aggregate.Inquiry;
import com.myhandjava.momentours.user.command.domain.aggregate.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
@Qualifier("queryMapper")
public interface UserMapper {
    User searchUserWithEmail(String userEmail);

    User searchUserWithNickname(String nickname);

    User getUserInfoWithEmail(String userEmail);

    User getUserPwd(String userEmail);

    List<Inquiry> getInquiryList(int userNo);

    User getUserIdWithPhone(String userPhone);
}
