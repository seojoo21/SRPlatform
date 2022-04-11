package com.srplatform.web;

import com.srplatform.domain.StudyReview;
import com.srplatform.domain.StudyReviewRepository;
import com.srplatform.web.dto.StudyReviewSaveRequestDto;
import com.srplatform.web.dto.StudyReviewUpdateRequestDto;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudyReviewControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudyReviewRepository srRep;

    // 단위 테스트가 끝날 때 마다 실행
    @After
    public void cleanup() throws Exception{
        srRep.deleteAll();
    }

    // 게시물 등록 테스트
    @Test
    public void testSave() {
        String title = "title";
        String content = "content";
        StudyReviewSaveRequestDto dto = StudyReviewSaveRequestDto.builder()
                                        .title(title).content(content).author("aaa").build();

        String url = "http://localhost:" + port + "/studyReview/new";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<StudyReview> list = srRep.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }

    // 게시물 수정 테스트
    @Test
    public void testUpdate() throws Exception {
        StudyReview savedSR = srRep.save(StudyReview.builder()
                .title("title")
                .content("content")
                .author("author").build());

        Long updateBno = savedSR.getBno();
        String updatedTitle = "title2";
        String updatedContent = "content2";

        StudyReviewUpdateRequestDto dto = StudyReviewUpdateRequestDto.builder()
                .title(updatedTitle).content(updatedContent).build();

        String url = "http://localhost:" + port + "/studyReview/" + updateBno;

        HttpEntity<StudyReviewUpdateRequestDto> requestEntity = new HttpEntity<>(dto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<StudyReview> list = srRep.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(updatedTitle);
        assertThat(list.get(0).getContent()).isEqualTo(updatedContent);
    }

    //BaseTimeEntity 테스트
    @Test
    public void testBaseTimeEntity(){
        LocalDateTime now = LocalDateTime.of(2022,04,11,0,0,0);
        srRep.save(StudyReview.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<StudyReview> list = srRep.findAll();

        StudyReview sr = list.get(0);

        System.out.println(">>>>>>regDate = " + sr.getRegDate()+ " updateDate = " + sr.getUpdateDate());

        assertThat(sr.getRegDate()).isAfter(now);
        assertThat(sr.getUpdateDate()).isAfter(now);
    }
}
