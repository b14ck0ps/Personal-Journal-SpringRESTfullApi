package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.JournalCommentService;

@RestController
@RequestMapping("/api")
public class JournalCommentController {
    private final JournalCommentService journalCommentService;

    public JournalCommentController(JournalCommentService journalCommentService) {
        this.journalCommentService = journalCommentService;
    }

    @GetMapping("/journal-comments")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(journalCommentService.findAll());
    }

    @PostMapping("/journal-comments")
    public ResponseEntity<?> save(domain.JournalEntryComment entity) {
        return ResponseEntity.ok().body(journalCommentService.save(entity));
    }

    @GetMapping("/journal-comments/{journalId}")
    public ResponseEntity<?> findAllByJournalEntryId(@PathVariable int journalId) {
        return ResponseEntity.ok().body(journalCommentService.findAllByJournalEntryId(journalId));
    }

}
