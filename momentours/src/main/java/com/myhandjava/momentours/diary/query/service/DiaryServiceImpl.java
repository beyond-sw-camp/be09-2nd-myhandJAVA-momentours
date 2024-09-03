package com.myhandjava.momentours.diary.query.service;

import com.myhandjava.momentours.diary.query.dto.CommentDTO;
import com.myhandjava.momentours.diary.query.dto.DiaryDTO;
import com.myhandjava.momentours.diary.query.repository.DiaryMapper;
import com.myhandjava.momentours.file.query.dto.FileDTO;
import com.myhandjava.momentours.file.query.repository.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service("diaryQueryServiceImpl")
@Slf4j
public class DiaryServiceImpl implements DiaryService {

    private final DiaryMapper diaryMapper;
    private final FileMapper fileMapper;

    @Autowired
    public DiaryServiceImpl(DiaryMapper diaryMapper, FileMapper fileMapper) {
        this.diaryMapper = diaryMapper;
        this.fileMapper = fileMapper;
    }

    // 일기 + 파일 + 댓글 조회
    @Override
    public List<DiaryDTO> selectDiary(DiaryDTO diaryDTO) {
        List<DiaryDTO> result = diaryMapper.selectDiary(diaryDTO);

        System.out.println("result = " + result);

        // 입력 문자열 형식에 맞는 DateTimeFormatter 사용
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        // 날짜를 포맷팅하여 새로운 리스트에 담기
        return result.stream()
                .map(diary -> {
                    DiaryDTO formattedDiary = new DiaryDTO();
                    formattedDiary.setDiaryNo(diary.getDiaryNo());
                    formattedDiary.setDiaryContent(diary.getDiaryContent());

                    // 일기 날짜 포맷팅
                    formattedDiary.setDiaryCreateDate(diary.getDiaryCreateDate() != null ?
                            LocalDateTime.parse(diary.getDiaryCreateDate(), inputFormatter).format(outputFormatter) : null);
                    formattedDiary.setDiaryUpdateDate(diary.getDiaryUpdateDate() != null ?
                            LocalDateTime.parse(diary.getDiaryUpdateDate(), inputFormatter).format(outputFormatter) : null);

                    // 댓글 날짜 포맷팅
                    List<CommentDTO> formattedComments = diary.getComments().stream()
                            .map(commnet -> {
                                CommentDTO commentDTO = new CommentDTO();
                                commentDTO.setCommentNo(commnet.getCommentNo());
                                commentDTO.setCommentContent(commnet.getCommentContent());

                                commentDTO.setCommentCreateDate(commnet.getCommentCreateDate() != null ?
                                        LocalDateTime.parse(commnet.getCommentCreateDate(), inputFormatter).format(outputFormatter) : null);
                                commentDTO.setCommentUpdateDate(commnet.getCommentUpdateDate() != null ?
                                        LocalDateTime.parse(commnet.getCommentUpdateDate(), inputFormatter).format(outputFormatter) : null);

                                return commentDTO;

                            })
                            .collect(Collectors.toList());

                    formattedDiary.setComments(formattedComments);

                    List<FileDTO> files = fileMapper.selectFilesByDiaryNo(diary.getDiaryNo());
                    formattedDiary.setFiles(files);

                    return formattedDiary;
                })
                .collect(Collectors.toList());
    }
}
