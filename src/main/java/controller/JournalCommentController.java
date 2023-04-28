package controller;

import DTOs.JournalCommentDto;
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
    public ResponseEntity<?> save(@RequestBody JournalCommentDto entity) {
        return ResponseEntity.ok().body(journalCommentService.save(entity));
    }

    @GetMapping("/journal-comments/{journalId}")
    public ResponseEntity<?> findAllByJournalEntryId(@PathVariable int journalId) {
        return ResponseEntity.ok().body(journalCommentService.findAllByJournalEntryId(journalId));
    }

    @DeleteMapping("/journal-comments/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok().body(journalCommentService.delete(id));
    }

    @PutMapping("/journal-comments")
    public ResponseEntity<?> update(@RequestBody JournalCommentDto entity) {
        return ResponseEntity.ok().body(journalCommentService.update(entity));
    }

}
