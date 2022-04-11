package com.srplatform.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyReviewUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public StudyReviewUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
