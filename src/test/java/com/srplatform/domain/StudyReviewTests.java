package com.srplatform.domain;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyReviewTests {

    @Autowired
    StudyReviewRepository srRep;

    // 단위 테스트가 끝날 때 마다 실행
    @After
    public void cleanup(){
        srRep.deleteAll();
    }

    // 게시글 불러오기
    @Test
    public void testGetList(){
        String title = "test 제목";
        String content = "test 내용";

        srRep.save(StudyReview.builder().title(title).content(content).author("test@test.com").build());

        List<StudyReview> list = srRep.findAll();

        StudyReview sr = list.get(0);
        assertThat(sr.getTitle()).isEqualTo(title);
        assertThat(sr.getContent()).isEqualTo(content);
    }
}
