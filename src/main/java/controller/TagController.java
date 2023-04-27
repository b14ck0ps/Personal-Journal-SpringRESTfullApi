package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TagService;

@RestController
@RequestMapping("/api")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getAllTags() {
        try {
            return ResponseEntity.ok().body(tagService.getAllTags());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No tags found");
        }
    }
}
