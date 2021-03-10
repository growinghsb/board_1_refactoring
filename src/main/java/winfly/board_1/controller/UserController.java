package winfly.board_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import winfly.board_1.controller.dto.UserDto;
import winfly.board_1.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contents", userService.findAll());
        return "home";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "write";
    }

    @PostMapping("write")
    public String write(UserDto dto) {
        userService.save(dto);
        return "redirect:/";
    }

}
