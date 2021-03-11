package winfly.board_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import winfly.board_1.controller.dto.ContentDto;
import winfly.board_1.entity.Content;
import winfly.board_1.service.ContentService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contents", contentService.findAll());
        return "home";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("contentDto", new ContentDto());
        return "write";
    }

    @PostMapping("write")
    public String write(ContentDto dto) {
        contentService.save(dto);
        return "redirect:/";
    }

    @GetMapping("content/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Content content = contentService.viewCountUp(id);
        ContentDto dto = new ContentDto();
        dto.setName(content.getName());
        dto.setTitle(content.getTitle());
        dto.setContent(content.getContent());
        model.addAttribute("content", dto);
        model.addAttribute("id", content.getId());
        return "detail";
    }

    @PostMapping("content/detail")
    public String detail(@RequestParam("id") Long id, ContentDto dto) {
        contentService.modify(dto, id);
        return "redirect:/";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") Long id){
        contentService.delete(id);
        return "redirect:/";
    }
}
