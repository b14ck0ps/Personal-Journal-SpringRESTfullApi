package service;

import DTOs.JournalEntryTagDto;
import domain.JournalEntryTag;
import org.springframework.stereotype.Service;
import repository.JournalEntryTagsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JournalEntryTagsService {
    private final JournalEntryTagsRepository journalEntryTagsRepository;

    public JournalEntryTagsService(JournalEntryTagsRepository journalEntryTagsRepository) {
        this.journalEntryTagsRepository = journalEntryTagsRepository;
    }

    public Boolean addTagToJournal(JournalEntryTag journalEntryTags) {
        try {
            journalEntryTagsRepository.save(journalEntryTags);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeTagFromJournal(JournalEntryTag journalEntryTags) {
        try {
            journalEntryTagsRepository.delete(journalEntryTags);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateTag(JournalEntryTag journalEntryTags) {
        try {
            journalEntryTagsRepository.save(journalEntryTags);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<JournalEntryTagDto> getAllTags() {
        return journalEntryTagsRepository.findAllTags();
    }

    public List<JournalEntryTagDto> getTagsByJournalEntryId(int id) {
        return journalEntryTagsRepository.findByJournalEntryId(id);
    }
}
