package rp.springboot.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.repository.JournalEntryRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntityService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntity save(JournalEntity journalEntity)
    {
        return journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getOne(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteJournalBy(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
