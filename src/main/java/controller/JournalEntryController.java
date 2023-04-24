package controller;

import domain.JournalEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.JournalEntryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/journalEntry")
public class JournalEntryController {
    private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerJournalEntry(@Valid @RequestBody JournalEntry journalEntry, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        journalEntryService.registerJournalEntry(journalEntry);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateJournalEntry(@Valid @RequestBody JournalEntry journalEntry, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        journalEntryService.updateJournalEntry(journalEntry);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable int id) {
        journalEntryService.deleteJournalEntry(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable int id) {
        return ResponseEntity.ok().body(journalEntryService.getJournalEntryById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJournalEntries() throws Exception {
        List<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries();
        return ResponseEntity.ok().body(journalEntries);
    }


    @GetMapping("/all/{username}")
    public ResponseEntity<?> getAllJournalEntriesByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(journalEntryService.getAllJournalEntriesByUserName(username));
    }

}
