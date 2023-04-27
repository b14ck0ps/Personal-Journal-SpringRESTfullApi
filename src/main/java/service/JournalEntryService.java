package service;

import DTOs.JournalEntryDto;
import domain.JournalEntry;
import domain.JournalEntryTag;
import domain.Tag;
import domain.User;
import org.springframework.stereotype.Service;
import repository.JournalEntryRepository;
import repository.JournalEntryTagsRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryTagsRepository journalEntryTagsRepository;
    private final TagService tagService;
    private final UserService userService;

    public JournalEntryService(JournalEntryRepository journalEntryRepository, JournalEntryTagsRepository journalEntryTagsRepository, TagService tagService, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.journalEntryTagsRepository = journalEntryTagsRepository;
        this.tagService = tagService;
        this.userService = userService;
    }


    public boolean registerJournalEntry(JournalEntryDto journalEntryDto) {
        try {

            User user = userService.getByUsername(journalEntryDto.getUsername());

            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setTitle(journalEntryDto.getTitle());
            journalEntry.setBody(journalEntryDto.getBody());
            journalEntry.setUser(user);
            journalEntryRepository.save(journalEntry);

            JournalEntryTag journalEntryTag = new JournalEntryTag();
            Tag tag = tagService.getTagById(journalEntryDto.getTagId());
            journalEntryTag.setJournalEntry(journalEntry);
            journalEntryTag.setTag(tag);
            journalEntryTagsRepository.save(journalEntryTag);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateJournalEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.update(journalEntry);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteJournalEntry(int id) {
        try {
            JournalEntry journalEntry = journalEntryRepository.findById(id);
            journalEntryRepository.delete(journalEntry);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public JournalEntry getJournalEntryById(int id) {
        try {
            return journalEntryRepository.findById(id);

        } catch (Exception e) {
            return null;
        }
    }

    public List<JournalEntry> getAllJournalEntries() {
        try {
            List<JournalEntry> journalEntries = journalEntryRepository.findAll();
            Collections.reverse(journalEntries);
            return journalEntries;
        } catch (Exception e) {
            return null;
        }
    }


    public List<JournalEntry> getAllJournalEntriesByUserName(String username) {
        try {
            List journalEntries = journalEntryRepository.findAllByUserName(username);
            Collections.reverse(journalEntries);
            return journalEntries;

        } catch (Exception e) {
            return null;
        }
    }

}
