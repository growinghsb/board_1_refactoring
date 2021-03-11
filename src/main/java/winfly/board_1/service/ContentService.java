package winfly.board_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winfly.board_1.controller.dto.ContentDto;
import winfly.board_1.entity.Content;
import winfly.board_1.repository.ContentJpaRepository;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@RequiredArgsConstructor
@Transactional// 항상 엔티티매니저는 트랜잭션 단위 안에서 동작하기 때문에 트랜잭션이 있어야 한다.
public class ContentService { // 테스트 코드 작성할 것

    //네이밍 할 때 기술이 먼저 오면 안된다. 기술을 의식하고 코딩하진 않기 때문이다.
    private final ContentJpaRepository contentJpaRepository;

    public Long save(ContentDto dto) {
        dto.setWriteTime(now().format(ofPattern("yyyy-MM-dd HH:mm:ss")));
        return contentJpaRepository.save(Content.createContent(dto));
    }

    public Content findOne(Long id) { // 바로 엔티티를 뷰로 보내지 말고, dto를 통해 진행, 리팩토링 할 것
        return contentJpaRepository.findOne(id);
    }

    public List<Content> findAll() {
        return contentJpaRepository.findAll();
    }

    public void modify(ContentDto dto, Long id) {
        dto.setModifyTime(now().format(ofPattern("yyyy-MM-dd HH:mm:ss")));
        Content.modifyContent(findOne(id), dto);

    }

    public Content viewCountUp(Long id) {
        Content content = findOne(id);
        Content.viewCountUp(content);
        return content;
    }

    public void delete(Long id) {
        contentJpaRepository.delete(findOne(id));
    }
}
