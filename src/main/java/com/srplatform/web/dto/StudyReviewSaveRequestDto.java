package com.srplatform.web.dto;

import com.srplatform.domain.StudyReview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyReviewSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public StudyReviewSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public StudyReview toEntity(){
        return StudyReview.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
