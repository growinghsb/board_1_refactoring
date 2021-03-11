package winfly.board_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity @Getter
// 생성자를 통한 무분별한 생성을 막기 위해 protected 기본 생성자를 만들 것, jpa 특성상 접근 제어자를 protected까지 허용.
// setter를 없애고, 비즈니스 메서드를 통해 하나의 메서드를 통해 create작업 진행할 것.
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String title;
    private String content;
    private String writeTime;
    private String modifyTime;
    private int viewCount;
}
