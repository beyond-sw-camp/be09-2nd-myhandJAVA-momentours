package com.myhandjava.momentours.momentcourse.query.service;

import com.myhandjava.momentours.momentcourse.query.dto.MomentCourseDTO;

import java.util.List;
import java.util.Map;

public interface MomentCourseService {

    // 전체 조회
    List<MomentCourseDTO> findAllMomentCourse(int momCourseCoupleNo);

    // 상세 조회
    List<MomentCourseDTO> findMomentCourseByMomCourseNo(MomentCourseDTO momentCourseDTO);

    List<MomentCourseDTO> searchMomentCourse(Map<String, Object> searchMap);
}
