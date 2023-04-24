package controller;

import domain.JournalEntryTag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.JournalEntryTagsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/journalEntryTags")
public class JournalEntryTagsController {
    private final JournalEntryTagsService journalEntryTagsService;

    public JournalEntryTagsController(JournalEntryTagsService journalEntryTagsService) {
        this.journalEntryTagsService = journalEntryTagsService;
    }

    @PostMapping("/addTagToJournal")
    public ResponseEntity<?> addTagToJournal(@Valid @RequestBody JournalEntryTag journalEntryTags, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        if (journalEntryTagsService.addTagToJournal(journalEntryTags)) {
            return ResponseEntity.ok("Tag added to journal");
        } else {
            return ResponseEntity.badRequest().body("Tag not added to journal");
        }
    }

    @DeleteMapping("/removeTagFromJournal")
    public ResponseEntity<?> removeTagFromJournal(@Valid @RequestBody JournalEntryTag journalEntryTags, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        if (journalEntryTagsService.removeTagFromJournal(journalEntryTags)) {
            return ResponseEntity.ok("Tag removed from journal");
        } else {
            return ResponseEntity.badRequest().body("Tag not removed from journal");
        }
    }

    @GetMapping("/getByJournalEntryId/{id}")
    public ResponseEntity<?> getTagsByJournalEntryId(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(journalEntryTagsService.getTagsByJournalEntryId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No tags found for journal entry");
        }
    }

    @GetMapping("/getAllTags")
    public ResponseEntity<?> getAllTags() {
        try {
            return ResponseEntity.ok().body(journalEntryTagsService.getAllTags());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No tags found");
        }
    }
}

