package winfly.board_1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import winfly.board_1.controller.dto.ContentDto;
import winfly.board_1.entity.Content;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ContentServiceTest {

    @Autowired
    private ContentService contentService;
    @Autowired
    private EntityManager em;

    @Test
    public void 저장_테스트() {
        //given (~ 가정했을 때)
        Long contentId = getContentId();

        //when (~ 할 때), then (그렇다면)
        assertThat(contentId).isNotNull();
    }

    @Test
    public void 단건_조회_테스트() {
        //given (~ 가정했을 때)
        Long contentId = getContentId();
        Content content = contentService.findOne(contentId);

        //when (~ 할 때), then (그렇다면)
        assertThat(content).isNotNull();
        assertThat(contentId).isEqualTo(content.getId());
        assertThat(content.getName()).isEqualTo("한승범");

    }

    @Test
    public void 수정_테스트() {
        //given (~ 가정했을 때)
        Long contentId = getContentId(); // 저장하고
        Content content = contentService.findOne(contentId); // 찾아오고
        ContentDto dto = new ContentDto(); // dto 생성해서 값 수정 값 세팅하고
        dto.setName("한승범");
        dto.setTitle("수정수정");
        dto.setContent("이것도 수정수정수정");

        //when (~ 할 때), then (그렇다면)

        // 메서드에 전달하고
        Content.modifyContent(content, dto);

        // db에 반영한다.
        em.flush();
        // 깨끗히 비워주고
        em.clear();

        // 다시 조회
        content = contentService.findOne(contentId);

        //이건 수정 했어도, 값이 같기 때문에 같아야 한다.
        assertThat(content.getName()).isEqualTo("한승범");
        //이건 수정이 반영되었으면 이전 데이터와 같지 않을 것이다.
        assertThat(content.getTitle()).isNotEqualTo("테스트");
        // 이건 수정이 반영됬으니 값이 같을 것이다.
        assertThat(content.getContent()).isEqualTo("이것도 수정수정수정");
        // null 체크
        assertThat(content).isNotNull();
        // 같은 id 값인지 체크
        assertThat(contentId).isEqualTo(content.getId());
    }

    @Test
    public void 전체_조회_테스트() {
        //given (~ 가정했을 때)
        for (int i = 0; i < 3; i++) {
            getContentId();
        }
        List<Content> contents = contentService.findAll();

        //when (~ 할 때), then (그렇다면)
        assertThat(contents.size()).isEqualTo(3);
        assertThat(contents.size()).isNotZero();
        assertThat(contents).isNotNull();
        assertThat(contents).isNotEmpty();
    }

    @Test
    public void 삭제_테스트() {
        //given (~ 가정했을 때)
        Long contentId = getContentId();

        //when (~ 할 때), then (그렇다면)
        contentService.delete(contentId);
        assertThat(contentService.findOne(contentId)).isNull();
    }

    @Test
    public void 조회수_증가_테스트() {
        //given (~ 가정했을 때)
        Long contentId = getContentId();
        Content content = contentService.viewCountUp(contentId);


        //when (~ 할 때), then (그렇다면) 
        assertThat(content.getViewCount()).isNotZero();
        assertThat(content.getViewCount()).isEqualTo(1);
    }

    private Long getContentId() {
        ContentDto dto = new ContentDto();
        dto.setName("한승범");
        dto.setTitle("테스트");
        dto.setContent("테스트 컨텐츠");
        Long contentId = contentService.save(dto);
        return contentId;
    }
}