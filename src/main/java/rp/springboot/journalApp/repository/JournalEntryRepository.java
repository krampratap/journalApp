package rp.springboot.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rp.springboot.journalApp.entities.JournalEntity;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {
}
