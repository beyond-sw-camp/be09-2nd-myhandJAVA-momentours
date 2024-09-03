package com.myhandjava.momentours.randomquestion.query.repository;

import com.myhandjava.momentours.randomquestion.command.domain.aggregate.RandomQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RandomQuestionMapper {
    RandomQuestion findCurrentRandomQuestionByCoupleNo(int coupleNo);

    List<RandomQuestion> findAllRandomQuestionByCoupleNo(int userNo);

    RandomQuestion findRandomQuestionByDate(Map<String, Object> map);

    List<RandomQuestion> findRandomQuestionByKeyword(Map<String, Object> map);
}
