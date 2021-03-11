package winfly.board_1.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDto {

    private String name;
    private String title;
    private String content;
    private String writeTime;
    private String modifyTime;
    private int viewCount;
}
