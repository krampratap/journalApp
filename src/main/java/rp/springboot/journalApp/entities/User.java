package rp.springboot.journalApp.entities;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId userId;

    @Indexed(unique = true)
    @NonNull //lombok
    private String username;
    @NonNull //lombok
    private String password;
    @DBRef //we are creating a reference to JournalEntity
    private List<JournalEntity> journalEntities;
    private List<String> roles;
}
