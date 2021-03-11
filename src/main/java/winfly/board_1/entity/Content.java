package winfly.board_1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import winfly.board_1.controller.dto.ContentDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 생성자를 통한 무분별한 생성을 막기 위해 protected 기본 생성자를 만들 것
 * jpa 특성상 접근 제어자를 protected까지 허용.
 * setter를 없애고, 비즈니스 메서드를 통해 하나의 메서드를 통해 create작업 진행할 것.
 * 객체의 응집도를 높히고 결합도를 줄일 것.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String title;
    private String content;
    private String writeTime;
    private String modifyTime;
    private int viewCount;

    public static Content createContent(ContentDto dto) {
        Content content = new Content();
        content.name = dto.getName();
        content.title = dto.getTitle();
        content.content = dto.getContent();
        content.writeTime = dto.getWriteTime();
        return content;
    }

    public static void modifyContent(Content content, ContentDto dto) {
        content.name = dto.getName();
        content.title = dto.getTitle();
        content.content = dto.getContent();
        content.modifyTime = dto.getModifyTime();
    }

    public static void viewCountUp(Content content) {
        content.viewCount = content.getViewCount() + 1;
    }
}
