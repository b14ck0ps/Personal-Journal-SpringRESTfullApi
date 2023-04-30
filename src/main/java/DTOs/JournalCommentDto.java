package DTOs;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class JournalCommentDto {
    private int id;
    private String Username;
    @NotBlank
    private String body;
    private int journal_entry_id;
    private int user_id;
    private String userImage;

    private LocalDateTime created_at = LocalDateTime.now();

    public JournalCommentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getJournal_entry_id() {
        return journal_entry_id;
    }

    public void setJournal_entry_id(int journal_entry_id) {
        this.journal_entry_id = journal_entry_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
