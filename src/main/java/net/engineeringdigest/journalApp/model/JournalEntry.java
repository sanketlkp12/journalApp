package net.engineeringdigest.journalApp.model;

import lombok.*;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

    public JournalEntry(ObjectId id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
