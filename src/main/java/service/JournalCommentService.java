package service;

import DTOs.JournalCommentDto;
import domain.JournalEntryComment;
import org.springframework.stereotype.Service;
import repository.JournalCommentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalCommentService {
    private final JournalCommentRepository journalCommentRepository;
    private final UserService userService;
    private final JournalEntryService journalEntryService;

    public JournalCommentService(JournalCommentRepository journalCommentRepository, UserService userService, JournalEntryService journalEntryService) {
        this.journalCommentRepository = journalCommentRepository;
        this.userService = userService;
        this.journalEntryService = journalEntryService;
    }

    public Boolean save(JournalCommentDto entity) {
        return journalCommentRepository.save(convertToEntity(entity));
    }

    public Boolean update(JournalEntryComment entity) {
        return journalCommentRepository.update(entity);
    }

    public Boolean delete(JournalEntryComment entity) {
        return journalCommentRepository.delete(entity);
    }

    public JournalEntryComment findById(int id) {
        return journalCommentRepository.findById(id);
    }

    public List<JournalCommentDto> findAll() {
        return convertToDto(journalCommentRepository.findAll());
    }

    public java.util.List<JournalCommentDto> findAllByJournalEntryId(int journalEntryId) {
        List<JournalEntryComment> journalEntryComments = journalCommentRepository.findAllByJournalEntryId(journalEntryId);
        return convertToDto(journalEntryComments);

    }

    private List<JournalCommentDto> convertToDto(List<JournalEntryComment> journalEntryComments) {
        return journalEntryComments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JournalCommentDto convertToDto(JournalEntryComment journalEntryComment) {
        JournalCommentDto journalCommentDto = new JournalCommentDto();
        journalCommentDto.setId(journalEntryComment.getId());
        journalCommentDto.setBody(journalEntryComment.getBody());
        journalCommentDto.setJournal_entry_id(journalEntryComment.getJournalEntry().getId());
        journalCommentDto.setUser_id(journalEntryComment.getUser().getId());
        journalCommentDto.setCreated_at(journalEntryComment.getCreatedAt());
        return journalCommentDto;
    }

    private JournalEntryComment convertToEntity(JournalCommentDto journalCommentDto) {
        JournalEntryComment journalEntryComment = new JournalEntryComment();
        journalEntryComment.setId(journalCommentDto.getId());
        journalEntryComment.setBody(journalCommentDto.getBody());
        journalEntryComment.setJournalEntry(journalEntryService.getJournalEntryById(journalCommentDto.getJournal_entry_id()));
        journalEntryComment.setUser(userService.getUserById(journalCommentDto.getUser_id()));
        journalEntryComment.setCreatedAt(journalCommentDto.getCreated_at());
        return journalEntryComment;
    }
}
