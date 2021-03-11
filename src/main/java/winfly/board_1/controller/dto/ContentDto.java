package winfly.board_1.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String title;
    private String content;
    private int viewCount;
}
