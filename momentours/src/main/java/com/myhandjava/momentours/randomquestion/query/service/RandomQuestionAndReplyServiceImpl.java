package com.myhandjava.momentours.randomquestion.query.service;

import com.myhandjava.momentours.randomquestion.command.domain.aggregate.RandomQuestion;
import com.myhandjava.momentours.randomquestion.command.domain.aggregate.RandomReply;
import com.myhandjava.momentours.randomquestion.query.dto.RandomQuestionAndReplyDTO;
import com.myhandjava.momentours.randomquestion.query.dto.RandomQuestionDTO;
import com.myhandjava.momentours.randomquestion.query.dto.RandomReplyDTO;
import com.myhandjava.momentours.randomquestion.query.repository.RandomQuestionMapper;
import com.myhandjava.momentours.randomquestion.query.repository.RandomReplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("RandomQAndRQueryService")
@Slf4j
public class RandomQuestionAndReplyServiceImpl implements RandomQuestionAndReplyService {

    private final RandomQuestionMapper randomQuestionMapper;
    private final RandomReplyMapper randomReplyMapper;
//    private final CoupleService coupleService;

    @Autowired
    public RandomQuestionAndReplyServiceImpl(RandomQuestionMapper randomQuestionMapper , RandomReplyMapper randomReplyMapper) {
        this.randomQuestionMapper = randomQuestionMapper;
        this.randomReplyMapper = randomReplyMapper;
    }

    // 최신 질문을 조회하기 위해선 회원 테이블 -> 커플 테이블 -> 질문 중 최신 1개의 과정이 요구된다
    // 커플의 번호를 알기 위해선 Mapper.xml에서 쿼리에서 join을 통해 얻어내는 건지, 아니면 coupleService를 호출해서 얻어내는 건지 선생님께 물어볼 것
    @Override
    public RandomQuestionDTO findCurrentRandomQuestionByCoupleNo(int coupleNo) {
//        int coupleNo = coupleService.getCoupleByUserNo(userNo);
        RandomQuestion randomQuestion = randomQuestionMapper.findCurrentRandomQuestionByMemberNo(coupleNo);  // coupleNo가 들어가야 맞지만 오류가 생기지 않도록 userNo 넣어놓음
        RandomQuestionDTO randomQuestionDTO = new RandomQuestionDTO(
                randomQuestion.getRandQuesNo(), randomQuestion.getRandQuesCreateDate(), randomQuestion.getRandQuesContent(),
                randomQuestion.getRandQuesReply(), randomQuestion.getRandQuesIsDeleted(), randomQuestion.getRandQuesCoupleNo()
        );
        log.info("반환된 값: {}", randomQuestionDTO);
        return randomQuestionDTO;
    }

    @Override
    public List<RandomQuestionDTO> findAllRandomQuestionByCoupleNo(int coupleNo) {
        List<RandomQuestion> randomQuestions = randomQuestionMapper.findAllRandomQuestionByMemberNo(coupleNo);  // userNo -> coupleNo로 바뀔 예정

        List<RandomQuestionDTO> randomQuestionDTOList = randomQuestions.stream().map(randomQuestion ->
                new RandomQuestionDTO(randomQuestion.getRandQuesNo(), randomQuestion.getRandQuesCreateDate(),
                        randomQuestion.getRandQuesContent(), randomQuestion.getRandQuesReply(),
                        randomQuestion.getRandQuesIsDeleted(), randomQuestion.getRandQuesCoupleNo()
                        )).collect(Collectors.toList());

        log.info("반환된 값: {}", randomQuestionDTOList);
        return randomQuestionDTOList;
    }

    @Override
    public RandomQuestionDTO findRandomQuestionByDate(Map<String, Object> map) {
        RandomQuestion randomQuestion = randomQuestionMapper.findRandomQuestionByDate(map);
        RandomQuestionDTO randomQuestionDTO = new RandomQuestionDTO(
                randomQuestion.getRandQuesNo(), randomQuestion.getRandQuesCreateDate(), randomQuestion.getRandQuesContent(),
                randomQuestion.getRandQuesReply(), randomQuestion.getRandQuesIsDeleted(), randomQuestion.getRandQuesCoupleNo()
        );

        log.info("반환된 값: {}", randomQuestionDTO);
        return randomQuestionDTO;
    }

    @Override
    public List<RandomQuestionDTO> findRandomQuestionByKeyword(Map<String, Object> map) {
        List<RandomQuestion> randomQuestions = randomQuestionMapper.findRandomQuestionByKeyword(map);

        List<RandomQuestionDTO> randomQuestionDTOList = randomQuestions.stream().map(randomQuestion ->
                new RandomQuestionDTO(randomQuestion.getRandQuesNo(), randomQuestion.getRandQuesCreateDate(),
                        randomQuestion.getRandQuesContent(), randomQuestion.getRandQuesReply(),
                        randomQuestion.getRandQuesIsDeleted(), randomQuestion.getRandQuesCoupleNo()
                )).collect(Collectors.toList());

        log.info("반환된 값: {}", randomQuestionDTOList);
        return randomQuestionDTOList;
    }

    @Override
    public RandomReplyDTO findRandomReplyByQuestionNoAndUserNo(Map<String, Object> map) {
        RandomReply randomReply;
        RandomReplyDTO randomReplyDTO;
        try {
            randomReply = randomReplyMapper.findRandomReplyByQuestionNoAndUserNo(map);
            if(randomReply != null) {
                randomReplyDTO = new RandomReplyDTO(
                        randomReply.getRandomReplyNo(), randomReply.getRandomReplyContent(), randomReply.getRandomReplyUserNo(),
                        randomReply.getRandomQuestionNo(), randomReply.getRandomReplyIsDeleted(), randomReply.getRandomCoupleNo()
                );
            } else {
                randomReplyDTO = createEmptyReplyDTO();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("회원번호와 질문 번호로 질문에 대합 답변 조회결과: {}", randomReplyDTO);
        return randomReplyDTO;
    }

    private RandomReplyDTO createHiddenReplyDTO() {
        return new RandomReplyDTO(0, "조회를 위해선 답변을 작성해주세요",
                0, 0, 0, 0);
    }

    private RandomReplyDTO createEmptyReplyDTO() {
        return new RandomReplyDTO(0, "텅", 0,
                0, 0, 0);
    }

    @Override
    public List<RandomQuestionAndReplyDTO> findAllRandomQuestionAndRepliesByUserNoAndCoupleNo(int coupleNo , int userNo) {
        // 회원 번호로 모든 랜덤 질문 가져오기
        List<RandomQuestionDTO> randomQuestionList = findAllRandomQuestionByCoupleNo(coupleNo);
//        int partnerNo = coupleService.getPartnerNoByUserNo(userNo);

        // 질문과 답변 DTO 생성
        List<RandomQuestionAndReplyDTO> result = new ArrayList<>();
        for (RandomQuestionDTO question : randomQuestionList) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userNo", userNo);
            userMap.put("randomQuestionNo", question.getRandQuesNo());

            Map<String, Object> partnerMap = new HashMap<>();
            partnerMap.put("userNo", userNo); // 파트너 회원번호로 변경 필요
            partnerMap.put("randomQuestionNo", question.getRandQuesNo());

            RandomReplyDTO userReplyDTO = findRandomReplyByQuestionNoAndUserNo(userMap);
            RandomReplyDTO partnerReplyDTO = findRandomReplyByQuestionNoAndUserNo(partnerMap);
            boolean canViewPartnerReply = userReplyDTO.getRandomReplyContent() != "텅";

            RandomQuestionAndReplyDTO dto = new RandomQuestionAndReplyDTO(
                    question,
                    userReplyDTO,
                    canViewPartnerReply ? partnerReplyDTO : createHiddenReplyDTO(),
                    canViewPartnerReply
            );
            result.add(dto);
        }

        return result;
    }
}
