package com.srplatform.web;

import com.srplatform.service.StudyReviewService;
import com.srplatform.web.dto.StudyReviewResponseDto;
import com.srplatform.web.dto.StudyReviewSaveRequestDto;
import com.srplatform.web.dto.StudyReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/studyReview")
@RestController
public class StudyReviewController {

    private final StudyReviewService studyReviewService;

    // 등록
    @PostMapping("/new")
    public Long save(@RequestBody StudyReviewSaveRequestDto dto) {
        return studyReviewService.save(dto);
    }

    // 수정
    @PutMapping("/{bno}")
    public Long update(@PathVariable Long bno, @RequestBody StudyReviewUpdateRequestDto dto) {
        return studyReviewService.update(bno, dto);
    }
    // 조회
    @GetMapping("/{bno}")
    public StudyReviewResponseDto get(@PathVariable Long bno) {
        return studyReviewService.findById(bno);
    }
}
