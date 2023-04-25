package service;

import domain.JournalEntry;
import org.springframework.stereotype.Service;
import repository.JournalEntryRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }


    public boolean registerJournalEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);
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
