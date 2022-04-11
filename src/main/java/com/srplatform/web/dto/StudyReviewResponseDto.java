package com.srplatform.web.dto;

import com.srplatform.domain.StudyReview;
import lombok.Getter;

@Getter
public class StudyReviewResponseDto {

    private Long bno;
    private String title;
    private String content;
    private String author;

    public StudyReviewResponseDto(StudyReview entity){
        this.bno = entity.getBno();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
