package rp.springboot.journalApp.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal_entries")
@Data
public class JournalEntity {
    @Id
    private ObjectId id;
    private String title;
    private String content;
}
