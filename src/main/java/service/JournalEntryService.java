package service;

import DTOs.JournalEntryDto;
import DTOs.JournalEntryTagDto;
import domain.JournalEntry;
import domain.JournalEntryTag;
import domain.Tag;
import domain.User;
import org.springframework.stereotype.Service;
import repository.JournalEntryRepository;
import repository.JournalEntryTagsRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
            journalEntry.setDeleted(true);
            journalEntryRepository.update(journalEntry);
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

    public JournalEntryTagDto getJournalEntryTagById(int id) {
        try {
            return journalEntryTagsRepository.findByJournalEntryId(id).get(0);

        } catch (Exception e) {
            return null;
        }
    }

    public List<JournalEntryDto> getAllJournalEntries() {
        try {
            List<JournalEntry> journalEntries = journalEntryRepository.findAll();
            return getJournalEntryDtos(journalEntries);
        } catch (Exception e) {
            return null;
        }
    }


    public List<JournalEntryDto> getAllJournalEntriesByUserName(String username) {
        try {
            List<JournalEntry> journalEntries = journalEntryRepository.findAllByUserName(username);
            return getJournalEntryDtos(journalEntries);

        } catch (Exception e) {
            return null;
        }
    }

    private List<JournalEntryDto> getJournalEntryDtos(List<JournalEntry> journalEntries) {
        Collections.reverse(journalEntries);
        List<JournalEntryDto> journalEntryDtos = new ArrayList<>();
        for (JournalEntry journalEntry : journalEntries) {
            JournalEntryDto journalEntryDto = new JournalEntryDto();
            journalEntryDto.setId(journalEntry.getId());
            journalEntryDto.setTitle(journalEntry.getTitle());
            journalEntryDto.setBody(journalEntry.getBody());
            journalEntryDto.setUsername(journalEntry.getUser().getUsername());
            journalEntryDto.setUserImage(journalEntry.getUser().getImage());
            journalEntryDto.setCreatedAt(journalEntry.getCreatedAt());
            journalEntryDto.setUpdatedAt(journalEntry.getUpdatedAt());
            JournalEntryTagDto journalEntryTag = getJournalEntryTagById(journalEntry.getId());
            journalEntryDto.setTagId(journalEntryTag.getTagId());
            journalEntryDto.setTagName(tagService.getTagById(journalEntryTag.getTagId()).getName());
            journalEntryDtos.add(journalEntryDto);
        }

        return journalEntryDtos;
    }

}
