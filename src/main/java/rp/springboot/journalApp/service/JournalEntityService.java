package rp.springboot.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.repository.JournalEntryRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntityService {

    //private final Logger logger = LoggerFactory.getLogger(JournalEntityService.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private RedisService redisService;

    public JournalEntity save(JournalEntity journalEntity)
    {
        log.info("logging");
        return journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntity getOne(ObjectId id){
        JournalEntity journalEntity = redisService.get("key_" + id, JournalEntity.class);
        if(journalEntity!=null)
        {
            return journalEntity;
        }
        else{
            JournalEntity journalEntity1 = journalEntryRepository.findById(id).orElseThrow();
            if(journalEntity1!=null){
                redisService.set(id.toString(),journalEntity1,100L);
            }
        }

    }

    public void deleteJournalBy(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
