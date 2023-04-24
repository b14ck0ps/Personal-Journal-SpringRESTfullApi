package DTOs;

public class JournalEntryTagDto {
    private Integer id;
    private Integer journalEntryId;
    private Integer tagId;

    public JournalEntryTagDto(Integer id, Integer journalEntryId, Integer tagId) {
        this.id = id;
        this.journalEntryId = journalEntryId;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJournalEntryId() {
        return journalEntryId;
    }

    public void setJournalEntryId(Integer journalEntryId) {
        this.journalEntryId = journalEntryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
