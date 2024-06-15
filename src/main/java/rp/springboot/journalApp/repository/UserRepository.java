package rp.springboot.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
    List<User> findByJournalEntities(JournalEntity journalEntity);
}
