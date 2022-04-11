package com.srplatform.service;

import com.srplatform.domain.StudyReview;
import com.srplatform.domain.StudyReviewRepository;
import com.srplatform.web.dto.StudyReviewResponseDto;
import com.srplatform.web.dto.StudyReviewSaveRequestDto;
import com.srplatform.web.dto.StudyReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StudyReviewService {

    public final StudyReviewRepository srRep;

    // 등록
    @Transactional
    public Long save(StudyReviewSaveRequestDto dto) {
        return srRep.save(dto.toEntity()).getBno();
    }

    // 수정
    @Transactional
    public Long update(Long bno, StudyReviewUpdateRequestDto dto) {
        StudyReview studyReview = srRep.findById(bno).orElseThrow
                (()->new IllegalArgumentException("해당 게시글이 없습니다: bno=" + bno));
        studyReview.update(dto.getTitle(), dto.getContent());
        return bno;
    }

    // 조회
    public StudyReviewResponseDto findById(Long bno){
        StudyReview entity = srRep.findById(bno).orElseThrow
                (()->new IllegalArgumentException("해당 게시글이 없습니다: bno=" + bno));
        return new StudyReviewResponseDto(entity);
    }
}
